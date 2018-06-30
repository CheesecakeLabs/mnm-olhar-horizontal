package br.com.maonamassa.olharhorizontal.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import br.com.maonamassa.olharhorizontal.R
import br.com.maonamassa.olharhorizontal.modelos.Organizacao
import kotlinx.android.synthetic.main.activity_cadastro.*

/**
 * Created by Aluno on 16/06/2018.
 */
class CadastroActivity : AppCompatActivity() {
    var participante = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    }

    private fun concluirBotao() {
        if (senha.text.toString() != repetirSenha.text.toString()) {

            return
        }

        val usuario = Organizacao()


        usuario.nome = nomeCompleto?.text.toString()
        usuario.email = email?.text.toString()
        usuario.senha = senha?.text.toString()
        usuario.dataNasc = dataNascimento?.text.toString()
        usuario.cnpj = CNPJ?.text.toString()
        usuario.endereço = localizacao?.text.toString()




    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}