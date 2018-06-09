package br.com.maonamassa.olharhorizontal.activities

/**
 * Created by Eduardo on 28/04/2018.
 */

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.maonamassa.olharhorizontal.R
import br.com.maonamassa.olharhorizontal.modelos.ONG
import kotlinx.android.synthetic.main.activity_map.*

class MapActivity : AppCompatActivity() {
    var ong: ONG? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        ong = intent.getParcelableExtra("ong")
        setupView()
    }
    private fun setupView() {
        descricao_da_ong.text = ong?.descricao ?: "null"
    }

}
