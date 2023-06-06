package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnFetchNormalQuote).setOnClickListener {
            startActivity(Intent(this, QuoteListActivity::class.java))
        }

        findViewById<Button>(R.id.btnFetchPagingQuote).setOnClickListener {
            startActivity(Intent(this, PaginationQuoteListActivity::class.java))
        }

    }
}