package br.com.maonamassa.olharhorizontal.modelos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Aluno on 07/07/2018.
 */
class RespostaCadastro {

    @SerializedName("token")
    @Expose
    var token: String? = null

    @SerializedName("user")
    @Expose
    var organizacao: Organizacao? = null

}