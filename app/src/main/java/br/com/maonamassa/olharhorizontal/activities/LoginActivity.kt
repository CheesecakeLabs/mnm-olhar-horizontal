package br.com.maonamassa.olharhorizontal.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import br.com.maonamassa.olharhorizontal.R
import br.com.maonamassa.olharhorizontal.modelos.LoginRequest
import br.com.maonamassa.olharhorizontal.utils.CadastroApi
import br.com.maonamassa.olharhorizontal.utils.RetrofitHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import android.app.ProgressDialog
import br.com.maonamassa.olharhorizontal.utils.SessionHelper


class LoginActivity: AppCompatActivity() {
    var dialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        SessionHelper.setup(this)

        if (SessionHelper.recuperarToken() != null) {
            openListActivity()
        }

        botaoCadastrar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener { login(emailEditText.text.toString(), passwordTextField.text.toString()) }

        withoutLogin.setOnClickListener {
            val intent = Intent(this, ListaActivity::class.java)
            startActivity(intent)
        }
    }

    private fun openListActivity() {
        val intent = Intent(this, ListaActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun login(email: String, password: String) {
        showLoading()
        val retrofit = RetrofitHelper.getRetrofit(false)
        val cadastroApi = retrofit.create(CadastroApi::class.java)
        cadastroApi.login(LoginRequest(email, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    dialog?.hide()
                    openListActivity()
                }, {
                    dialog?.hide()
                    showMessageDialog("Erro", "Aconteceu um erro, tente novamente mais tarde.")
                })
    }

    private fun showMessageDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setNeutralButton("OK", { _, _ ->

        })
        builder.setTitle(title)
        builder.setMessage(message)
        val dialog = builder.create()
        dialog?.show()
    }

    private fun showLoading() {
        val dialog = ProgressDialog.show(this, "",
                "Realizando login", true)
        dialog.show()

        this.dialog = dialog
    }

}
