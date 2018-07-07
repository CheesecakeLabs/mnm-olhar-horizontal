package br.com.maonamassa.olharhorizontal.utils

import android.util.Log
import br.com.maonamassa.olharhorizontal.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * This object provides the default retrofit instance of the application.
 * Also builds retrofit and has a helper function to parse error responses.
 */
object RetrofitHelper {
    private var retrofit: Retrofit? = null
    private var usingAuth = false

    fun getRetrofit(useAuth: Boolean): Retrofit {
        if (retrofit == null || usingAuth != useAuth) {
            rebuildRetrofit(useAuth)
        }
        return retrofit!! //this can be forced because it will be created on rebuildRetrofit if was null
    }

    fun buildGson(): Gson {
        return GsonBuilder().create()
    }

    fun <T> parseError(throwable: Throwable, classType: Class<T>): T? {
        if (throwable is HttpException) {
            val body = throwable.response().errorBody()
            val adapter = buildGson().getAdapter(classType)
            try {
                return adapter.fromJson(body.string())
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return null
    }

    //region Private

    private fun rebuildRetrofit(useAuth: Boolean) {
        val client = buildOkHttpClient(useAuth)

        retrofit = Retrofit.Builder()
                .baseUrl(Constants.URLs.baseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(buildGson()))
                .client(client)
                .build()
    }

    private fun buildOkHttpClient(useAuth: Boolean): OkHttpClient {
        usingAuth = useAuth
        var builder = OkHttpClient.Builder()
        if (useAuth) builder = addTokenInterceptor(builder)
        builder = addLoggingInterceptor(builder)

        return builder.build()
    }

    private fun addTokenInterceptor(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        val tokenInterceptor = Interceptor { chain ->
            var newResquest = chain.request()
            val accessToken = SessionHelper.recuperarToken()
             if (accessToken?.isNotBlank() == true) {
                 newResquest = newResquest.newBuilder().addHeader("Authorization", "Token $accessToken").build()
             }
            chain.proceed(newResquest)
        }
        builder.addNetworkInterceptor(tokenInterceptor)
        return builder
    }

    private fun addLoggingInterceptor(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        // This adds logging for all requests if the app is run in Debug mode
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor { message -> Log.d("HttpLoggingInterceptor", message) }
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addNetworkInterceptor(httpLoggingInterceptor)
        }
        return builder
    }

    //endregion
}