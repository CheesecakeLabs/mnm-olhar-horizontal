package br.com.maonamassa.olharhorizontal.utils

import br.com.maonamassa.olharhorizontal.modelos.ONG
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by CELTA-8514 on 25/11/2017.
 */

interface ProjetosApi {

    @GET("api/projects/")
    fun getProjects(): Observable<List<ONG>>
}