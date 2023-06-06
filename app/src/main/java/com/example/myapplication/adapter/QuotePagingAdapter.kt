package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.myapplication.databinding.ItemQuotesBinding
import com.example.myapplication.listeners.QuoteClickListener
import com.example.myapplication.model.Quote

class QuotePagingAdapter(
    private val quoteClickListener: QuoteClickListener
) : PagingDataAdapter<Quote, QuoteViewHolder>(QuoteComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemQuotesBinding.inflate(layoutInflater, parent, false)
        return QuoteViewHolder(binding, quoteClickListener)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        getItem(position)?.let { quote -> holder.bindQuote(quote) }
    }

}