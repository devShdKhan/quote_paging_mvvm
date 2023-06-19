package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.model.Quote

class FragmentQuoteDetail : Fragment(R.layout.fragment_quote_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable("quote", Quote::class.java)?.let {
            view.findViewById<TextView>(R.id.txtQuote)?.text = it.content
        }
    }
}