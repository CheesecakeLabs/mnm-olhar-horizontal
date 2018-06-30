package br.com.maonamassa.olharhorizontal.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import br.com.maonamassa.olharhorizontal.R
import br.com.maonamassa.olharhorizontal.modelos.ONG
import br.com.maonamassa.olharhorizontal.utils.OngAdapter
import br.com.maonamassa.olharhorizontal.utils.OngClickListener
import br.com.maonamassa.olharhorizontal.utils.ProjetosApi
import br.com.maonamassa.olharhorizontal.utils.RetrofitHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_lista.*

/**
 * Created by Aluno on 16/06/2018.
 */
class BuscaActivity : AppCompatActivity(), OngClickListener {
    override fun onOngClicked(ong: ONG) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busca)
        carregarProjetos()
    }
    fun carregarProjetos() {
        RetrofitHelper
                .getRetrofit(false)
                .create(ProjetosApi::class.java).getProjects()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.results
                    val adapter=OngAdapter(it.results, this)
                    ongRecyclerView.adapter=adapter

                }, {

                })


    }
}