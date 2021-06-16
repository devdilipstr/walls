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
        val list = listOf<String>("https://cdn.pixabay.com/photo/2016/12/16/15/25/christmas-1911637_960_720.jpg","https://cdn.pixabay.com/photo/2016/06/02/02/33/triangles-1430105_960_720.png","https://cdn.pixabay.com/photo/2017/12/10/15/16/white-horse-3010129_960_720.jpg","https://images.unsplash.com/photo-1623138997967-8c57eb6b1f35?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80","https://images.unsplash.com/photo-1623003641967-c43abbede243?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=751&q=80","https://images.unsplash.com/photo-1595398228634-0bb5a98d21c4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80")
        bestwall.adapter = BestWallAdapter(list)
        bestwall.layoutManager = LinearLayoutManager(this,LinearLayout.HORIZONTAL,false)

        //category_recycler
        val category_list = listOf<category>(category("566231","https://cdn.pixabay.com/photo/2016/12/16/15/25/christmas-1911637_960_720.jpg","Abstract"),category("566231","https://cdn.pixabay.com/photo/2016/12/16/15/25/christmas-1911637_960_720.jpg","Abstract"),category("566231","https://cdn.pixabay.com/photo/2016/12/16/15/25/christmas-1911637_960_720.jpg","Abstract"),category("45454","https://images.unsplash.com/photo-1595398228634-0bb5a98d21c4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80","Nature"),category("45454","https://images.unsplash.com/photo-1595398228634-0bb5a98d21c4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80","Nature"),category("45454","https://images.unsplash.com/photo-1595398228634-0bb5a98d21c4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80","Nature"),category("45454","https://images.unsplash.com/photo-1595398228634-0bb5a98d21c4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80","Nature"),category("45454","https://images.unsplash.com/photo-1595398228634-0bb5a98d21c4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80","Nature"))
        categories.adapter = categoryAdapter(this,category_list)
        categories.layoutManager = GridLayoutManager(this,2)
        categories.addItemDecoration(GridDecoration(2,45,true))

        // search bar
        searchbar.setOnEditorActionListener{v,actionId,event->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                if(searchbar.text.toString() != "null"){
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