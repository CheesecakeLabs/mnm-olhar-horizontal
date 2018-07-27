package br.com.maonamassa.olharhorizontal.modelos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Aluno on 07/07/2018.
 */
class Cadastro {

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("password")
    @Expose
    var senha: String? = null

}
