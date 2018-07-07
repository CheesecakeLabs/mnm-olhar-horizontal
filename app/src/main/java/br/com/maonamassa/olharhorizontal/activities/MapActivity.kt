package br.com.maonamassa.olharhorizontal.activities

/**
 * Created by Eduardo on 28/04/2018.
 */

/**
 * Modified by Tiago on 23/06/2018.
 */

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import br.com.maonamassa.olharhorizontal.R
import br.com.maonamassa.olharhorizontal.modelos.ONG
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_map.*
import kotlinx.android.synthetic.main.item_ong.view.*

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    var map: GoogleMap? = null

    var ong: ONG? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        ong = intent.getParcelableExtra("ong")
        setupView()

        mapa.onCreate(savedInstanceState)

        mapa.getMapAsync(this)

        button.setOnClickListener {

            val i = Intent(Intent.ACTION_VIEW, Uri.parse (ong?.organizacao?.url))
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


    private fun setupView() {
        descricao_da_ong.text = ong?.descricao ?: "Descrição Teste"

        texto_endereco.text = ong?.endereco ?: "Endereço teste"

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Picasso.with(this).load(ong?.fotoUrl).into(imagemDescricao)


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
