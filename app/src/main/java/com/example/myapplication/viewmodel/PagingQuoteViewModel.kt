package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.myapplication.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PagingQuoteViewModel @Inject constructor(
    repository: QuoteRepository
) : ViewModel() {

    val pagingQuotes = repository.fetchQuoteWithPagination().cachedIn(viewModelScope)

}