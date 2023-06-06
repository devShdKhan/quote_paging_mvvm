package com.example.myapplication.repository.pagingquote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.myapplication.network.QuoteApiInterface
import com.example.myapplication.pagging.QuotePageSource
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class PagingQuoteRepositoryImpl @Inject constructor(
    private val quoteApiInterface: QuoteApiInterface
) : PagingQuoteRepository {

    override fun fetchQuoteWithPagination() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 60),
        pagingSourceFactory = { QuotePageSource(quoteApiInterface) }
    ).flow

}