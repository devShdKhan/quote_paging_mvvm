package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.myapplication.repository.pagingquote.PagingQuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PagingQuoteViewModel @Inject constructor(
    repository: PagingQuoteRepository
) : ViewModel() {

    val pagingQuotes = repository.fetchQuoteWithPagination().cachedIn(viewModelScope)

}