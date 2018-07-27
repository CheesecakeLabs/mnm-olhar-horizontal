package br.com.maonamassa.olharhorizontal.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.R.styleable.Toolbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View.GONE
import br.com.maonamassa.olharhorizontal.R
import br.com.maonamassa.olharhorizontal.modelos.ONG
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhes.*
import java.text.SimpleDateFormat

class DetalhesActivity: AppCompatActivity(), OnMapReadyCallback {

    var map: GoogleMap? = null

    var ong: ONG? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)
        ong = intent.getParcelableExtra("ong")
        setupView()

        mapa.onCreate(savedInstanceState)

        mapa.getMapAsync(this)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        botaoOng.setOnClickListener{
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(ong?.organizacao?.url))
            startActivity(i)
        }

        toolbar.setNavigationOnClickListener {
            finish()
        }

    }

    override fun onStart() {
        super.onStart()
        mapa.onStart()
    }

    override fun onPause() {
        super.onPause()
        mapa.onPause()
    }
    override fun onResume() {
        super.onResume()
        mapa.onResume()
    }

    override fun onStop() {
        super.onStop()
        mapa.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapa.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapa.onLowMemory()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

    private fun setupView(){
        nomeDetalhes.text = ong?.organizacao?.nome
        title = ong?.nome


        val date = SimpleDateFormat("yyyy-MM-dd").parse(ong?.dataProjeto)
        dataDetalhes.text = SimpleDateFormat("dd/MM/yyyy").format(date).toString()


        enderecoDetalhes.text = ong?.endereco
        descricaoDetalhes.text = ong?.descricao
        Picasso.with(this).load(ong?.fotoUrl).into(imagemDescricao)

        if (ong?.organizacao?.url == null) {
            botaoOng.visibility = GONE
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap

        val florianopolis = LatLng(ong?.latitude?.toDouble() ?: -27.594, ong?.longitude?.toDouble() ?: -48.5421)
        map?.setMinZoomPreference(12f)
        map?.moveCamera(CameraUpdateFactory.newLatLng(florianopolis))

        val markerOptions = MarkerOptions()
        markerOptions.position(florianopolis)
        markerOptions.title(ong?.nome).snippet(ong?.endereco)
        map?.addMarker(markerOptions)
    }

}
