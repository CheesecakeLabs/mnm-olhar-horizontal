package br.com.maonamassa.olharhorizontal.activities

/**
 * Created by Eduardo on 28/04/2018.
 */

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.maonamassa.olharhorizontal.R
import br.com.maonamassa.olharhorizontal.modelos.ONG
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import kotlinx.android.synthetic.main.activity_map.*

class MapActivity : AppCompatActivity(), OnMapReadyCallback {


    var ong: ONG? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        ong = intent.getParcelableExtra("ong")
        setupView()

        mapa.onCreate(savedInstanceState)

        mapa.getMapAsync(this)

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
        descricao_da_ong.text = ong?.descricao ?: "null"


    }
    override fun onMapReady(p0: GoogleMap?) {
     //   TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
