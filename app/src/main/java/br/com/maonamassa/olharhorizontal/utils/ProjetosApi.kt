package br.com.maonamassa.olharhorizontal.utils

import br.com.maonamassa.olharhorizontal.modelos.ONG
import br.com.maonamassa.olharhorizontal.modelos.PaginaOng
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by CELTA-8514 on 25/11/2017.
 */

interface ProjetosApi {

    @GET("api/projects/")
    fun getProjects(): Observable<PaginaOng>

    @POST("api/projects/")
    fun createProject(@Body project: ONG): Observable<PaginaOng>
}