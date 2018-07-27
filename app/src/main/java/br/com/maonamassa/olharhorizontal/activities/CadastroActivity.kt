package br.com.maonamassa.olharhorizontal.activities

import android.os.Bundle
import android.os.Message
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import br.com.maonamassa.olharhorizontal.R
import br.com.maonamassa.olharhorizontal.modelos.Cadastro
import br.com.maonamassa.olharhorizontal.modelos.Organizacao
import br.com.maonamassa.olharhorizontal.modelos.RespostaCadastro
import br.com.maonamassa.olharhorizontal.utils.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_cadastro.*
import java.text.SimpleDateFormat

/**
 * Created by Aluno on 16/06/2018.
 */
class CadastroActivity : AppCompatActivity() {
    var participante = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SessionHelper.setup(this)
        setContentView(R.layout.activity_cadastro)
        radiogroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.ONGbutton) {

                participante = false
                DatadeNascimentotextimput.visibility = View.GONE
                CNPJtextinput.visibility = View.VISIBLE
                localizacaotextimput.visibility = View.VISIBLE
            }


            if (checkedId == R.id.particebutton) {

                participante = true
                DatadeNascimentotextimput.visibility = View.VISIBLE
                CNPJtextinput.visibility = View.GONE
                localizacaotextimput.visibility = View.GONE
            }
        }

        title = "Cadastro"
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        botaoConcluir.setOnClickListener(){

            concluirBotao()
        }
        DateInputMask(dataNascimento).listen()
    }

    private fun concluirBotao() {
        if (senha.text.toString() != repetirSenha.text.toString()) {
            return
        }

        val usuario = Cadastro()
        usuario.senha = senha?.text.toString()
        usuario.email = email?.text.toString()

        val retrofit = RetrofitHelper.getRetrofit(false)
        val cadastroApi = retrofit.create(CadastroApi::class.java)
        cadastroApi.cadastrar(usuario)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ respostaCadastro ->
                    processarResposta(respostaCadastro)
                    Log.d("teste", respostaCadastro.token)
                }, { error ->
                    Log.d("Erro", error.message )
                    showMessageDialog("Erro", "Erro ao efetuar o cadastro")
                })

        usuario.nome = nomeCompleto?.text.toString()
        usuario.email = email?.text.toString()
        usuario.senha = senha?.text.toString()
        usuario.dataNasc = dataNascimento?.text.toString()
        usuario.cnpj = CNPJ?.text.toString()
        usuario.endereco = localizacao?.text.toString()
    }

    private fun processarResposta (respostaCadastro: RespostaCadastro) {
        // REVER ISTO AQUI
        salvarToken(respostaCadastro.token ?: return)
        atualizarUsuario(respostaCadastro.organizacao?.id ?: return)
    }

    private fun atualizarUsuario (id: Int) {

        val organizacao = Organizacao()
        if (participante) {
            organizacao.entidade = "P"
        }
        else {
            organizacao.entidade = "O"
        }
        organizacao.cnpj = CNPJ?.text.toString()
        organizacao.nome = nomeCompleto?.text.toString()
        organizacao.dataNasc =  convertDateToBackendFormat(dataNascimento?.text.toString())
        organizacao.endereço = localizacao?.text.toString()
        organizacao.id = id
        val retrofit = RetrofitHelper.getRetrofit(true)
        val cadastroApi = retrofit.create(CadastroApi::class.java)
        cadastroApi.atualizarCadastro(id, organizacao)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ organizacao ->
                    Log.d("teste", organizacao.email)
                    showMessageDialog("Sucesso!", "Cadastro efetuado")
                }, { error ->
                    Log.d("Erro", error.message )
                    showMessageDialog("Erro", "Erro ao efetuar o cadastro")
                })


    }

    private fun salvarToken (token: String) {

        SessionHelper.salvarToken(token)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun convertDateToBackendFormat(date: String): String{
        val inputFormat = SimpleDateFormat("dd/mm/yyyy")
        val outputFormat = SimpleDateFormat("yyyy-mm-dd")
        val date = inputFormat.parse(date)
        return outputFormat.format(date)
    }

    private fun showMessageDialog(title: String, message: String){
        var builder = AlertDialog.Builder(this)
        builder.setNeutralButton("OK", { _, _ ->

        })
        builder.setTitle(title)
        builder.setMessage(message)
        val dialog = builder.create()
        dialog?.show()
    }
}
