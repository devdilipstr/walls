package com.example.walls

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@Suppress("DEPRECATION")
class CategoryAdapter(private val context:Context, private val list:MutableList<Category>): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val categoryid:TextView = itemView.findViewById(R.id.category_id)
        val categoryimg:ImageView= itemView.findViewById(R.id.category_img)
        val categorytext:TextView= itemView.findViewById(R.id.category_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view:View = inflater.inflate(R.layout.category_layout,parent,false)
        return CategoryViewHolder(view).listen{position->
            val item = list[position]
            val intent = Intent(parent.context,SearchActivity::class.java)
            intent.putExtra("query",item.title)
            startActivity(parent.context,intent,Bundle.EMPTY)
        }
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val data =  list[position]
        holder.categoryid.text = data.id
        val urls = data.cover_photo.urls
        Glide.with(context).load(urls["thumb"]).into(holder.categoryimg)
        holder.categorytext.text = data.title
    }

    override fun getItemCount(): Int {
        return list.size
    }
    private fun <T:RecyclerView.ViewHolder> T.listen(event:(position:Int)->Unit) :T{
        itemView.setOnClickListener{
            event.invoke(adapterPosition)
        }
        return this
    }
}