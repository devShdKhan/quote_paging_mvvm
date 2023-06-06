package com.example.myapplication.pagging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.model.Quote
import com.example.myapplication.network.QuoteApiInterface

class QuotePageSource(
    private val quoteApiInterface: QuoteApiInterface
) : PagingSource<Int, Quote>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Quote> {
        return try {
            val currentPage = params.key ?: 1
            val response = quoteApiInterface.getQuotes(currentPage)
            LoadResult.Page(
                data = response.results,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (currentPage == response.totalCount) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Quote>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}