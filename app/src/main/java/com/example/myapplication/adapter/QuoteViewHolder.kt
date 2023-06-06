package com.example.myapplication.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemQuotesBinding
import com.example.myapplication.listeners.QuoteClickListener
import com.example.myapplication.model.Quote

class QuoteViewHolder(
    private val binding: ItemQuotesBinding,
    private val quoteClickListener: QuoteClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindQuote(quote: Quote) {
        binding.txtQuote.text = quote.content
        binding.root.setOnClickListener {
            quoteClickListener.onQuoteClick(quote)
        }
    }

}