package br.com.maonamassa.olharhorizontal.utils

import br.com.maonamassa.olharhorizontal.modelos.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

/**
 * Created by Aluno on 07/07/2018.
 */
interface CadastroApi {

    @POST("api/auth/register")
    fun cadastrar(@Body cadastro: Cadastro): Observable<RespostaCadastro>

    @PATCH("api/users/{id}/")
    fun atualizarCadastro(@Path("id") id: Int, @Body organizacao: Organizacao): Observable<Organizacao>


    @POST("api/auth/login")
    fun login(@Body loginRequest: LoginRequest): Single<RespostaCadastro>

}