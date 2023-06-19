package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R

class FragmentRouter : Fragment(R.layout.fragment_router) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnFetchNormalQuote).setOnClickListener {
            findNavController().navigate(R.id.action_FragmentRouter_to_FragmentQuoteList)
        }

        view.findViewById<Button>(R.id.btnFetchPagingQuote).setOnClickListener {
            findNavController().navigate(R.id.action_FragmentRouter_to_FragmentPaginationQuoteList)
        }
    }

}