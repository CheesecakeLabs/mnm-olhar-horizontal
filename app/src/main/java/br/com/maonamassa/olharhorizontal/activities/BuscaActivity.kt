package br.com.maonamassa.olharhorizontal.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import br.com.maonamassa.olharhorizontal.R
import br.com.maonamassa.olharhorizontal.modelos.ONG
import br.com.maonamassa.olharhorizontal.utils.*
import br.com.maonamassa.olharhorizontal.utils.adapters.OngBuscaAdapter
import br.com.maonamassa.olharhorizontal.utils.listemers.OngItemListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_lista.*

/**
 * Created by Aluno on 16/06/2018.
 */
class BuscaActivity : AppCompatActivity(), OngItemListener {

    val adapter = OngBuscaAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busca)
        setup()
        carregarProjetos()
    }

    fun setup(){
        ongRecyclerView.adapter = adapter
    }

    fun carregarProjetos() {
        RetrofitHelper
                .getRetrofit(false)
                .create(ProjetosApi::class.java).getProjects()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adapter.itens = it.results
                }, {
                    //TODO tratar erro
                    Log.d("carregarProjetos", "Erro ao carregar projetos")
                })


    }

    override fun ongPressionada(ong: ONG?) {
        val intent = Intent(this, DetalhesActivity::class.java)
        intent.putExtra("ong", ong)
        startActivity(intent)
    }
}