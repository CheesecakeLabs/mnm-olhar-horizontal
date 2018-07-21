package br.com.maonamassa.olharhorizontal.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import br.com.maonamassa.olharhorizontal.R
import br.com.maonamassa.olharhorizontal.modelos.ONG
import br.com.maonamassa.olharhorizontal.utils.OngAdapter
import br.com.maonamassa.olharhorizontal.utils.OngClickListener
import br.com.maonamassa.olharhorizontal.utils.ProjetosApi
import br.com.maonamassa.olharhorizontal.utils.RetrofitHelper
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_lista.*
import android.support.design.widget.Snackbar
import android.support.design.widget.FloatingActionButton
import android.view.View


class ListaActivity: AppCompatActivity(), OngClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        setupRecyclerView()
        getDataFromServer()
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
            }
        })
        title = "Olhar Horizontal"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_busca, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.itemBusca) {
            val intent = Intent(this, BuscaActivity::class.java)
            startActivity(intent)
        }
        return true
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
                }, { _ ->
            Log.e("Erro", "Deu ruim na API")
        }
        )

    }

    override fun onOngClicked(ong: ONG) {
        val proximaTela = Intent(this, DetalhesActivity::class.java)
        proximaTela.putExtra("ong", ong)
        startActivity(proximaTela)
    }
}