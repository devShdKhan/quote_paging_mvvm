package com.example.myapplication.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.example.myapplication.R
import com.example.myapplication.model.Quote

class QuoteDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quote_detail)

        IntentCompat.getParcelableExtra(intent, "quote", Quote::class.java)?.let {
            findViewById<TextView>(R.id.txtQuote)?.text = it.content
        }
    }
}