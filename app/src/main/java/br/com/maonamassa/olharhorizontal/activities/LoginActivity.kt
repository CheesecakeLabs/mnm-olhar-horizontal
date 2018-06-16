package br.com.maonamassa.olharhorizontal.activities

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import br.com.maonamassa.olharhorizontal.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        botaoCadastrar.setOnClickListener {
            var intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }
}