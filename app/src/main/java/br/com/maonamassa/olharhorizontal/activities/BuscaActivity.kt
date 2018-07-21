package br.com.maonamassa.olharhorizontal.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import br.com.maonamassa.olharhorizontal.R
import br.com.maonamassa.olharhorizontal.modelos.ONG
import br.com.maonamassa.olharhorizontal.utils.*
import br.com.maonamassa.olharhorizontal.utils.adapters.OngBuscaAdapter
import br.com.maonamassa.olharhorizontal.utils.listemers.OngItemListener
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_busca.*


/**
 * Created by Aluno on 16/06/2018.
 */
class BuscaActivity : AppCompatActivity(), OngItemListener {

    val adapter = OngBuscaAdapter(this)
    private var subscription: Disposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busca)
        setup()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return true
    }


    fun getDataFromServer(search: String) {
        subscription?.dispose()
        progress.visibility = VISIBLE
        val retrofit = RetrofitHelper.getRetrofit(useAuth = false)
        val apiInterface = retrofit.create(ProjetosApi::class.java)
        subscription = apiInterface?.searchProjects(search)?.subscribeOn(Schedulers.io())?.subscribe(
                { page ->
                    runOnUiThread { page?.let {
                        adapter.itens = it.results
                        progress.visibility = GONE
                    } }
                }, { _ ->
            Log.e("Erro", "Deu ruim na API")
        }
        )

    }


    fun setup(){
        ongRecyclerView.adapter = adapter
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        searchField.setOnEditorActionListener { textView, i, keyEvent ->
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
            true
        }
        searchField.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(text: Editable?) {
    getDataFromServer(text.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })
    }


    override fun ongPressionada(ong: ONG?) {
        val intent = Intent(this, DetalhesActivity::class.java)
        intent.putExtra("ong", ong)
        startActivity(intent)
    }
}