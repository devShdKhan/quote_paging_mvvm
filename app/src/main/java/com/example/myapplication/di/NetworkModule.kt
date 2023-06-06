package com.example.myapplication.di

import com.example.myapplication.network.QuoteApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    const val BASE_URL = "http://quotable.io/"

    @Provides
    @Singleton
    fun getQuoteApiInterface(retrofit: Retrofit) = retrofit.create(QuoteApiInterface::class.java)

    @Provides
    @Singleton
    fun getApiInterface(okHttp: OkHttpClient) =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun getOkHttp() = OkHttpClient.Builder().build()

}