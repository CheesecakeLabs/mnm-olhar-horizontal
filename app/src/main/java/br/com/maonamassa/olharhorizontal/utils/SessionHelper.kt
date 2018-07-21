package br.com.maonamassa.olharhorizontal.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Aluno on 07/07/2018.
 */
object SessionHelper {
    val tokenKey = "TOKEN"

    var sharedPreferences: SharedPreferences? = null

    fun setup(context: Context) {
        sharedPreferences = context.getSharedPreferences("mnm", 0)
    }

    fun salvarToken (token: String) {

        val editor = sharedPreferences?.edit()
        editor?.putString(tokenKey, token)
        editor?.apply()

    }

    fun recuperarToken (): String? {

        return sharedPreferences?.getString(tokenKey, null)

    }

}