package com.newsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.newsapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }
}