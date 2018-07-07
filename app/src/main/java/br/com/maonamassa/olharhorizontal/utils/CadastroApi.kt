package br.com.maonamassa.olharhorizontal.utils

import br.com.maonamassa.olharhorizontal.modelos.Cadastro
import br.com.maonamassa.olharhorizontal.modelos.PaginaOng
import br.com.maonamassa.olharhorizontal.modelos.RespostaCadastro
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST

/**
 * Created by Aluno on 07/07/2018.
 */
interface CadastroApi {

    @POST("api/auth/register")
    fun cadastrar (@Body cadastro: Cadastro): Observable<RespostaCadastro>

}