package com.example.walls

import android.annotation.SuppressLint
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class SearchActivity:AppCompatActivity(){
    lateinit var query_textview:TextView
    lateinit var resultnum_textview:TextView
    lateinit var result_recycler:RecyclerView
    lateinit var loadmore_button:Button
    var query = ""
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)
        //initialize the views
        query_textview = findViewById(R.id.query)
        resultnum_textview = findViewById(R.id.results)
        result_recycler = findViewById(R.id.query_recycler)
        loadmore_button = findViewById(R.id.loadmore)
        val intent  = intent
        query = intent.getStringExtra("query").toString()
        query_textview.text =  query
        var page = 1
        val results_found = 10
        searchquery(query,1)
        loadmore_button.setOnClickListener{
            page = page + 1
            searchquery(query,page)
        }
    }

    private fun searchquery(search:String,page:Int) {
        loadmore_button.visibility = View.GONE
        val bestwalls = RetroFetchObject.retrofetch.getWallByQuery(search,page)
        bestwalls.enqueue(object: retrofit2.Callback<QueryResult> {
            @SuppressLint("WrongConstant")
            override fun onResponse(call: Call<QueryResult>, response: Response<QueryResult>) {
                val result = response.body()
                val array:Array<Image>  = result!!.results
                result_recycler.adapter = WallAdapter(this@SearchActivity, array!!.toMutableList(),R.layout.staggererdwallthumb)
                result_recycler.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
               loadmore_button.visibility = View.VISIBLE
            }

            override fun onFailure(call: Call<QueryResult>, t: Throwable) {
                Toast.makeText(this@SearchActivity,"search failed", Toast.LENGTH_SHORT).show()
            }

        })
    }
}