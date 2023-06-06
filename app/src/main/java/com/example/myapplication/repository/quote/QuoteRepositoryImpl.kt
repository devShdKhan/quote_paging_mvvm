package com.example.myapplication.repository.quote

import com.example.myapplication.model.Resource
import com.example.myapplication.network.QuoteApiInterface
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

}