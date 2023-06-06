package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.adapter.QuoteAdapter
import com.example.myapplication.databinding.ActivityQuoteListBinding
import com.example.myapplication.listeners.QuoteClickListener
import com.example.myapplication.model.Quote
import com.example.myapplication.model.QuoteResponse
import com.example.myapplication.model.Resource
import com.example.myapplication.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuoteListActivity : AppCompatActivity(), QuoteClickListener {

    private val viewModel by viewModels<QuoteViewModel>()

    private val quoteAdapter by lazy { QuoteAdapter(this) }

    private lateinit var binding: ActivityQuoteListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRV()

        lifecycleScope.launch {
            viewModel.quotes.collect {
                when (it) {
                    is Resource.Error -> setupError(it.t)
                    is Resource.Success -> setupSuccess(it.data)
                    is Resource.Loading -> setupLoading()
                }
            }
        }
    }

    private fun setupRV() {
        binding.rv.adapter = quoteAdapter
    }

    private fun setupLoading() {
        binding.progress.visibility = View.VISIBLE
    }

    private fun setupError(data: Throwable) {
        binding.progress.visibility = View.GONE
        binding.txtError.text = data.message
    }

    private fun setupSuccess(data: QuoteResponse) {
        binding.progress.visibility = View.GONE
        quoteAdapter.submitList(data.results)
    }

    override fun onQuoteClick(quote: Quote) {
        val quoteDetailIntent = Intent(this, QuoteDetailActivity::class.java).apply {
            putExtra("quote", quote)
        }
        startActivity(quoteDetailIntent)
    }
}