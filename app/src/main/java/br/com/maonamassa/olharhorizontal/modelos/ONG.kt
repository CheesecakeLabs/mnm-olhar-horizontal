package br.com.maonamassa.olharhorizontal.modelos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel
import paperparcel.PaperParcelable
import java.util.*


@PaperParcel
  class ONG: PaperParcelable {
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

    @SerializedName("date")
    @Expose
    var dataProjeto: String? = null

    @SerializedName("description")
    @Expose
    var descricao: String? = null

    @SerializedName("address")
    @Expose
    var endereco: String? = null

    @SerializedName("longitude")
    @Expose
    var longitude: String? = null

    @SerializedName("latitude")
    @Expose
    var latitude: String? = null

    @SerializedName("time")
    @Expose
    var horario: String? = null

    @SerializedName("organization")
    @Expose
    var organizacao: Organizacao? = null


}