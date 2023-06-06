package com.example.myapplication.repository.pagingquote

import androidx.paging.PagingData
import com.example.myapplication.model.Quote
import kotlinx.coroutines.flow.Flow

interface PagingQuoteRepository {
    fun fetchQuoteWithPagination(): Flow<PagingData<Quote>>
}