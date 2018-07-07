package br.com.maonamassa.olharhorizontal.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.R.styleable.Toolbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Button
import br.com.maonamassa.olharhorizontal.R
import br.com.maonamassa.olharhorizontal.R.id.*
import br.com.maonamassa.olharhorizontal.modelos.ONG
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhes.*
import java.text.SimpleDateFormat

class DetalhesActivity: AppCompatActivity() {

    var ong: ONG? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)
        ong = intent.getParcelableExtra("ong")
        setupView()

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        botaoOng.setOnClickListener{
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(ong?.organizacao?.url))
            startActivity(i)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    private fun setupView(){
//        nomeDetalhes.text = ong?.organizacao?.nome
        title = ong?.nome
        dataDetalhes.text = SimpleDateFormat("dd/MM/yyyy").format(ong?.dataProjeto)
//        estadoDetalhes.text = ong?.estado
//        cidadeDetalhes.text = ong?.cidade
//        bairroDetalhes.text = ong?.bairro
        enderecoDetalhes.text = ong?.endereco
        descricaoDetalhes.text = ong?.descricao
        Picasso.with(this).load(ong?.fotoUrl).into(imagemDescricao)
    }


}
