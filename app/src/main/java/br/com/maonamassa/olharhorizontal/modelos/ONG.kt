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
    var dataProjeto: Date? = null

    @SerializedName("description")
    @Expose
    var descricao: String? = null

    @SerializedName("address")
    @Expose
    var endereco: String? = null

}