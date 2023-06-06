package com.example.myapplication.network

import com.example.myapplication.model.QuoteResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApiInterface {

    object Path {
        const val quotes = "quotes"
    }

    @GET(Path.quotes)
    suspend fun getQuotes(@Query("page") page: Int): QuoteResponse

}