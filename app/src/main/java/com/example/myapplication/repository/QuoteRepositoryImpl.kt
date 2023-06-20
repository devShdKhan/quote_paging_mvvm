package com.example.myapplication.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.myapplication.model.Resource
import com.example.myapplication.network.QuoteApiInterface
import com.example.myapplication.pagging.QuotePageSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuoteRepositoryImpl @Inject constructor(
    private val quoteApiInterface: QuoteApiInterface
) : QuoteRepository {

    override fun fetchQuoteInFlow(page: Int) = flow {
        try {
            val response = quoteApiInterface.getQuotes(page)
            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }

    override fun fetchQuoteWithPagination() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 60),
        pagingSourceFactory = { QuotePageSource(quoteApiInterface) }
    ).flow

}