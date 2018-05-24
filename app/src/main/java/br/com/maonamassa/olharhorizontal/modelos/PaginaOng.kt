package br.com.maonamassa.olharhorizontal.modelos

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PaginaOng {

    @SerializedName("count")
    @Expose
    var count: Int? = null

    @SerializedName("next")
    @Expose
    var next: String? = null

    @SerializedName("previous")
    @Expose
    var previous: String? = null

    @SerializedName("results")
    @Expose
    var results: List<ONG> = listOf()
}