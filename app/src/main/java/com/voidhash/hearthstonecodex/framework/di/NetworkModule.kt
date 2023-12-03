package com.voidhash.hearthstonecodex.framework.di

import com.voidhash.hearthstonecodex.framework.remote.api.HearthstoneAPI
import com.voidhash.hearthstonecodex.framework.remote.service.HearthstoneService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    private const val BASE_URL = "https://omgvamp-hearthstone-v1.p.rapidapi.com/"

    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {

        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor( Interceptor { chain ->
                val original: Request = chain.request()
                val request: Request = original.newBuilder()
                    .header("X-RapidAPI-Host", "omgvamp-hearthstone-v1.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "3a6e3481d9mshf9421db4165c108p1b0d8ajsn8a3c10e596c3")
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            })
            .connectTimeout(0, TimeUnit.SECONDS)
            .writeTimeout(0, TimeUnit.SECONDS)
            .readTimeout(0, TimeUnit.SECONDS)
            .build();
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    fun provideHearthstoneAPI(retrofit: Retrofit): HearthstoneAPI {
        return  retrofit.create(HearthstoneAPI::class.java)
    }

    val hearthstoneService = module {
        factory { HttpLoggingInterceptor() }
        factory { provideOkHttpClient(get()) }
        single { provideRetrofit(get()) }
        factory { provideHearthstoneAPI(get()) }
        factory { HearthstoneService(get()) }
    }
}