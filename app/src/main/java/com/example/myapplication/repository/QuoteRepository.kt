package com.example.myapplication.repository

import androidx.paging.PagingData
import com.example.myapplication.model.Quote
import com.example.myapplication.model.QuoteResponse
import com.example.myapplication.model.Resource
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    fun fetchQuoteInFlow(page: Int): Flow<Resource<QuoteResponse>>

    fun fetchQuoteWithPagination(): Flow<PagingData<Quote>>

}