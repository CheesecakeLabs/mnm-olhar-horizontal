package br.com.maonamassa.olharhorizontal.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import br.com.maonamassa.olharhorizontal.R
import br.com.maonamassa.olharhorizontal.modelos.ONG
import br.com.maonamassa.olharhorizontal.utils.OngAdapter
import br.com.maonamassa.olharhorizontal.utils.OngClickListener
import br.com.maonamassa.olharhorizontal.utils.ProjetosApi
import br.com.maonamassa.olharhorizontal.utils.RetrofitHelper
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_lista.*


class ListaActivity: AppCompatActivity(), OngClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        setupRecyclerView()
        getDataFromServer()
        title = "Olhar Horizontal"
    }

    fun setupRecyclerView() {
        ongRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun getDataFromServer() {
        val retrofit = RetrofitHelper.getRetrofit(useAuth = false)
        val apiInterface = retrofit.create(ProjetosApi::class.java)
        apiInterface?.getProjects()?.subscribeOn(Schedulers.io())?.subscribe(
                { page ->
                    runOnUiThread { page?.let { ongRecyclerView.adapter = OngAdapter(it.results, this) } }
                }, { error ->
            Log.e("Erro", "Deu ruim na API")
        }
        )

    }

    override fun onOngClicked(ong: ONG) {
        val proximaTela = Intent(this, MapActivity::class.java)
        proximaTela.putExtra("ong", ong)
        startActivity(proximaTela)
    }
}