package com.ma.development.a5oddam_archieve_app.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ma.development.a5oddam_archieve_app.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
    }
}