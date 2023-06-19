package com.example.myapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.os.BundleCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.model.Quote

class FragmentQuoteDetail : Fragment(R.layout.fragment_quote_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val quote = BundleCompat.getParcelable(requireArguments(), "quote", Quote::class.java)

        quote?.let {
            view.findViewById<TextView>(R.id.txtQuote)?.text = it.content
        }
    }
}