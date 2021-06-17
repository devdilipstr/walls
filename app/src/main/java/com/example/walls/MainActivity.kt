package com.example.walls

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
        getbestimagelist()
        //category_recycler
        getcategories()


        // search bar
        searchbar.setOnEditorActionListener{v,actionId,event->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                if(searchbar.text.length != 0){
                    val intent = Intent(this,SearchActivity::class.java)
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

    private fun getcategories() {
        val getcategory = RetroFetchObject.retrofetch.getCollection()
        getcategory.enqueue(object:Callback<MutableList<Category>>{
            override fun onResponse(
                call: Call<MutableList<Category>>,
                response: Response<MutableList<Category>>
            ) {
                val category_list:MutableList<Category>? = response.body()
                categories.adapter = CategoryAdapter(this@MainActivity,category_list!!)
                categories.layoutManager = GridLayoutManager(this@MainActivity,2)
                categories.addItemDecoration(GridDecoration(2,45,true))
            }

            override fun onFailure(call: Call<MutableList<Category>>, t: Throwable) {
                Log.i("errror","error")
            }

        })
    }

    private fun getbestimagelist() {
        val bestwalls = RetroFetchObject.retrofetch.getBestWalls()
        bestwalls.enqueue(object:Callback<MutableList<Image>>{
            @SuppressLint("WrongConstant")
            override fun onResponse(
                call: Call<MutableList<Image>>,
                response: Response<MutableList<Image>>
            ) {
                val list: MutableList<Image>? = response.body()
                bestwall.adapter = WallAdapter(this@MainActivity,list!!,R.layout.wallthumb)
                bestwall.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayout.HORIZONTAL,false)
            }

            override fun onFailure(call: Call<MutableList<Image>>, t: Throwable) {
                Log.i("error",t.toString())
            }

        })
    }
}