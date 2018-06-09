package br.com.maonamassa.olharhorizontal.modelos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import paperparcel.PaperParcel
import paperparcel.PaperParcelable
import java.util.*

/**
 * Created by CELTA-8514 on 25/11/2017.
 */


@PaperParcel
class Organizacao()  : PaperParcelable {
    companion object {
        @JvmField
        val CREATOR = PaperParcelOrganizacao.CREATOR
    }

    @SerializedName("document_number")
    @Expose
    var cnpj: String? = null

    @SerializedName("name")
    @Expose
    var nome: String? = null

    @SerializedName("entity")
    @Expose
    var entity: String? = null

    @SerializedName("birth_date")
    @Expose
    var birth_date: Date? = null

    @SerializedName("picture")
    @Expose
    var picture : String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null


}


