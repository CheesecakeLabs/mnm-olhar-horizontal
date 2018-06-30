package br.com.maonamassa.olharhorizontal.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.com.maonamassa.olharhorizontal.R
import kotlinx.android.synthetic.main.activity_cadastro.*

/**
 * Created by Aluno on 16/06/2018.
 */
class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        radiogroup.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.ONGbutton) {

                DatadeNascimentotextimput.visibility = View.GONE
                CNPJtextimput.visibility = View.VISIBLE
                localizacaotextimput.visibility = View.VISIBLE
            }


            if (checkedId == R.id.particebutton) {

                DatadeNascimentotextimput.visibility = View.VISIBLE
                CNPJtextimput.visibility = View.GONE
                localizacaotextimput.visibility = View.GONE
            }
        }
    }
}