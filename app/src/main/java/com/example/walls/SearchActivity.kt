package com.example.walls

import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

class SearchActivity:AppCompatActivity(){
    lateinit var
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.searchactivity)
        val intent  =  getIntent()
        val query = intent.getStringExtra("query")

    }
}