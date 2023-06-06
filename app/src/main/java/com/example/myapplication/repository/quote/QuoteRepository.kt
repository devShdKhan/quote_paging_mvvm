package com.example.myapplication.repository.quote

import com.example.myapplication.model.QuoteResponse
import com.example.myapplication.model.Resource
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    fun fetchQuoteInFlow(page: Int): Flow<Resource<QuoteResponse>>

}