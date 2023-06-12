package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.myapplication.R

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                Thread.sleep(3000)
                false
            }
        }

        findViewById<Button>(R.id.btnFetchNormalQuote).setOnClickListener {
            startActivity(Intent(this, QuoteListActivity::class.java))
        }

        findViewById<Button>(R.id.btnFetchPagingQuote).setOnClickListener {
            startActivity(Intent(this, PaginationQuoteListActivity::class.java))
        }

    }
}