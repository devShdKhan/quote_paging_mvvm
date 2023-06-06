package com.example.myapplication.listeners

import com.example.myapplication.model.Quote

interface QuoteClickListener {
    fun onQuoteClick(quote: Quote)
}