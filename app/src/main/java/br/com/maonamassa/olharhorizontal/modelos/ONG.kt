package br.com.maonamassa.olharhorizontal.modelos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel
import paperparcel.PaperParcelable
import java.util.*


@PaperParcel
  class ONG() : PaperParcelable {
    companion object {
        @JvmField
        val CREATOR = PaperParcelONG.CREATOR
    }

    @SerializedName("image")
    @Expose
    var fotoUrl: String? = null

    @SerializedName("name")
    @Expose
    var nome: String? = null

    @SerializedName("start_at")
    @Expose
    var dataProjeto: Date? = null

    @SerializedName("description")
    @Expose
    var descricao: String? = null

    @SerializedName("state")
    @Expose
    var estado: String? = null

    @SerializedName("city")
    @Expose
    var cidade: String? = null

    @SerializedName("address_1")
    @Expose
    var endereco: String? = null

    @SerializedName("address_2")
    @Expose
    var bairro: String? = null

    @SerializedName("organization")
    @Expose
    var organizacao: Organizacao? = null

    @SerializedName("")
    @Expose
    var url: String? = null

}