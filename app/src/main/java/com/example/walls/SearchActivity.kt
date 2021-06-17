package com.example.walls

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class SearchActivity:AppCompatActivity(){
    lateinit var query_textview:TextView
    lateinit var resultnum_textview:TextView
    lateinit var result_recycler:RecyclerView
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)
        //initialize the views
        query_textview = findViewById(R.id.query)
        resultnum_textview = findViewById(R.id.results)
        result_recycler = findViewById(R.id.query_recycler)
        val intent  = intent
        val query = intent.getStringExtra("query")
        query_textview.text =  query
        val results_found = 0
        resultnum_textview.text = results_found.toString() + "wallpapers found"
        val image  = Image("dsfsdf", mutableMapOf<String,String>("full" to "https://images.unsplash.com/photo-1564512480295-86e479d9b87c?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb", "thumb" to "https://images.unsplash.com/photo-1564512480295-86e479d9b87c?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max"))
        val list = mutableListOf<Image>(image,image,image)
        result_recycler.adapter =  WallAdapter(list)
        result_recycler.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

    }
}