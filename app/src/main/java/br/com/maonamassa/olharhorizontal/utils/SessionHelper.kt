package br.com.maonamassa.olharhorizontal.utils

import android.content.Context

/**
 * Created by Aluno on 07/07/2018.
 */
class SessionHelper(var context: Context) {

    val sharedPreferences = context.getSharedPreferences("mnm", 0)

    fun salvarToken

}