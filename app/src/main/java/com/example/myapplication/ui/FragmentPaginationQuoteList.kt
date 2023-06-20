package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import com.example.myapplication.R
import com.example.myapplication.adapter.QuotePagingAdapter
import com.example.myapplication.databinding.FragmentPaginationQuoteListBinding
import com.example.myapplication.listeners.QuoteClickListener
import com.example.myapplication.model.Quote
import com.example.myapplication.viewmodel.PagingQuoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentPaginationQuoteList : Fragment(), QuoteClickListener {

    private val viewModel by viewModels<PagingQuoteViewModel>()
    private val quotePagingAdapter by lazy { QuotePagingAdapter(this) }
    private lateinit var binding: FragmentPaginationQuoteListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaginationQuoteListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRV()
        registerFlowCollectors()
    }

    private fun registerFlowCollectors() {
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

    private fun setupRV() {
        binding.rv.adapter = quotePagingAdapter
    }

    private fun setupPagingList(pagingData: PagingData<Quote>) {
        quotePagingAdapter.submitData(lifecycle, pagingData)
    }

    private fun setupError(t: Throwable?) {
        binding.txtError.text = t?.message
    }

    override fun onQuoteClick(quote: Quote) {
        val bundle = bundleOf("quote" to quote)
        findNavController().navigate(
            R.id.action_FragmentPaginationQuoteList_to_FragmentQuoteDetail,
            bundle
        )
    }
}