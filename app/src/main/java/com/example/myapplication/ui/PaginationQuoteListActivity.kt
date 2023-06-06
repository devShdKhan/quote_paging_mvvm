package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.myapplication.adapter.QuotePagingAdapter
import com.example.myapplication.databinding.ActivityPaginationQuoteListBinding
import com.example.myapplication.listeners.QuoteClickListener
import com.example.myapplication.model.Quote
import com.example.myapplication.viewmodel.PagingQuoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PaginationQuoteListActivity : AppCompatActivity(), QuoteClickListener {

    private val viewModel by viewModels<PagingQuoteViewModel>()

    private val quotePagingAdapter by lazy { QuotePagingAdapter(this) }

    private lateinit var binding: ActivityPaginationQuoteListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaginationQuoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rv.adapter = quotePagingAdapter

        lifecycleScope.launch {
            viewModel.pagingQuotes.collect {
                setupPagingList(it)
            }
        }

        lifecycleScope.launch {
            quotePagingAdapter.loadStateFlow.collect {
                binding.progress.isVisible = it.source.refresh is LoadState.Loading
                binding.prependProgress.isVisible = it.source.prepend is LoadState.Loading
                binding.appendProgress.isVisible = it.source.append is LoadState.Loading
                setupError((it.source.refresh as? LoadState.Error)?.error)
            }
        }
    }

    private fun setupPagingList(pagingData: PagingData<Quote>) {
        quotePagingAdapter.submitData(lifecycle, pagingData)
    }

    private fun setupError(t: Throwable?) {
        binding.txtError.text = t?.message
    }

    override fun onQuoteClick(quote: Quote) {
        val quoteDetailIntent = Intent(this, QuoteDetailActivity::class.java).apply {
            putExtra("quote", quote)
        }
        startActivity(quoteDetailIntent)
    }
}