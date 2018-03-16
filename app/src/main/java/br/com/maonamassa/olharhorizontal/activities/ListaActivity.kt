package br.com.maonamassa.olharhorizontal.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import br.com.maonamassa.olharhorizontal.R
import br.com.maonamassa.olharhorizontal.R.id.ongRecyclerView
import br.com.maonamassa.olharhorizontal.modelos.ONG
import br.com.maonamassa.olharhorizontal.utils.OngAdapter
import br.com.maonamassa.olharhorizontal.utils.OngClickListener
import br.com.maonamassa.olharhorizontal.utils.ProjetosApi
import br.com.maonamassa.olharhorizontal.utils.RetrofitHelper
import kotlinx.android.synthetic.main.activity_lista.*
import java.text.SimpleDateFormat
import io.reactivex.schedulers.Schedulers


class ListaActivity : AppCompatActivity(), OngClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        setupRecyclerView()
        getDataFromServer()
        //popularRecyclerView()
        title = "Olhar Horizontal"
    }

    fun setupRecyclerView() {
        ongRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun popularRecyclerView() {

        val formater = SimpleDateFormat("dd/MM/yyyy")
        var listaDeOngs: MutableList<ONG> = mutableListOf()

        val ong1 = ONG()
        ong1.dataProjeto = formater.parse("12/12/2018")
        ong1.nome = "Olhar Horizontal"
        ong1.descricao = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        ong1.fotoUrl = "https://imagens.mdig.com.br/natureza/flores_cerejeira_bonitas_mundo_03.jpg"
        ong1.estado = "Santa Catarina"
        ong1.cidade = "Antônio Carlos"
        ong1.bairro = "Centro"
        ong1.endereco = "Manoel Antonio Lopes"

        val ong2 = ONG()
        ong2.dataProjeto = formater.parse("25/12/2017")
        ong2.nome = "Ato de Bondade"
        ong2.descricao = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        ong2.fotoUrl = "https://www.belasmensagens.com.br/wp-content/uploads/2014/03/frases-bonitas.jpg"
        ong2.estado = "São Paulo"
        ong2.cidade = "São Paulo"
        ong2.bairro = "Centro"
        ong2.endereco = "Avenida Paulista"

        val ong3 = ONG()
        ong3.dataProjeto = formater.parse("06/08/2018")
        ong3.nome = "Amigos da Natureza"
        ong3.descricao = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        ong3.fotoUrl = "https://img00.deviantart.net/9bb2/i/2010/154/7/0/above_the_clouds_by_bo0xvn.jpg"
        ong3.estado = "Rio Grande do Sul"
        ong3.cidade = "Porto Alegra"
        ong3.bairro = "Centro"
        ong3.endereco = "Uma rua"

        val ong4 = ONG()
        ong4.dataProjeto = formater.parse("07/07/2017")
        ong4.nome = "Maré do Bem"
        ong4.descricao = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
        ong4.fotoUrl = "https://thumbs.dreamstime.com/b/paisagens-bonitas-do-mar-43239195.jpg"
        ong4.estado = "Rio de Janeiro"
        ong4.cidade = "Rio de Janeiro"
        ong4.bairro = "Centro"
        ong4.endereco = "Outra rua"

        listaDeOngs.add(ong1)
        listaDeOngs.add(ong2)
        listaDeOngs.add(ong3)
        listaDeOngs.add(ong4)

        ongRecyclerView.adapter = OngAdapter(listaDeOngs, this)

    }

    fun getDataFromServer() {
        val retrofit = RetrofitHelper.getRetrofit(useAuth = false)
        val apiInterface = retrofit.create(ProjetosApi::class.java)
        val ongs = apiInterface?.getProjects()?.subscribeOn(Schedulers.io())?.subscribe(
                { result ->
                    runOnUiThread { result?.let { ongRecyclerView.adapter = OngAdapter(result, this)}}
                }, { error -> Log.e("Erro",  "Deu ruim na API")
        }
        )


    }

    override fun onOngClicked(ong: ONG) {
        val proximaTela = Intent(this, DetalhesActivity::class.java)
        proximaTela.putExtra("ong", ong)
        startActivity(proximaTela)
    }
}