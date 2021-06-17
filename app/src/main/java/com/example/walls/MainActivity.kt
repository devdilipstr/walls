package com.example.walls

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    lateinit var bestwall:RecyclerView
    lateinit var categories:RecyclerView
    lateinit var searchbar:EditText
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //get views
        bestwall = findViewById(R.id.bestwall)
        categories = findViewById(R.id.categories_recycler)
        searchbar =  findViewById(R.id.search_bar)
        //best_recycler
        val image  = Image("dsfsdf", mutableMapOf<String,String>("full" to "https://images.unsplash.com/photo-1564512480295-86e479d9b87c?ixlib=rb-1.2.1&q=85&fm=jpg&crop=entropy&cs=srgb", "thumb" to "https://images.unsplash.com/photo-1564512480295-86e479d9b87c?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max"))
        val list = mutableListOf<Image>(image,image,image)
        bestwall.adapter = WallAdapter(list)
        bestwall.layoutManager = LinearLayoutManager(this,LinearLayout.HORIZONTAL,false)

        //category_recycler
        val category  = category("sdfdsf",image,"Nature")
        val category_list = mutableListOf<category>(category,category,category)
        categories.adapter = categoryAdapter(this,category_list)
        categories.layoutManager = GridLayoutManager(this,2)
        categories.addItemDecoration(GridDecoration(2,45,true))

        // search bar
        searchbar.setOnEditorActionListener{v,actionId,event->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                if(searchbar.text.toString() !== "null" ){
                    var intent = Intent(this,SearchActivity::class.java)
                    intent.putExtra("query",searchbar.text.toString())
                    startActivity(intent)
                }else{
                    Toast.makeText(this,"Please Enter Something to search",Toast.LENGTH_SHORT).show()
                }
                true
            }else{
                false
            }
        }
    }
}