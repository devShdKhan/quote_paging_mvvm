package com.example.myapplication.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.adapter.QuoteAdapter
import com.example.myapplication.databinding.FragmentQuoteListBinding
import com.example.myapplication.listeners.QuoteClickListener
import com.example.myapplication.model.Quote
import com.example.myapplication.model.QuoteResponse
import com.example.myapplication.model.Resource
import com.example.myapplication.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentQuoteList : Fragment(), QuoteClickListener {

    private val viewModel by viewModels<QuoteViewModel>()

    private val quoteAdapter by lazy { QuoteAdapter(this) }

    private lateinit var binding: FragmentQuoteListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuoteListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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
        val bundle = bundleOf("quote" to quote)
        findNavController().navigate(R.id.action_FragmentQuoteList_to_FragmentQuoteDetail, bundle)
    }
}