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
import org.w3c.dom.Text
import java.lang.reflect.Type

class categoryAdapter(val context:Context,val list:MutableList<category>): RecyclerView.Adapter<categoryAdapter.categoryViewHolder>() {
    class categoryViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val category_id = itemView.findViewById<TextView>(R.id.category_id)
        val category_img= itemView.findViewById<ImageView>(R.id.category_img)
        val category_text = itemView.findViewById<TextView>(R.id.category_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): categoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view:View = inflater.inflate(R.layout.category_layout,parent,false)
        return categoryViewHolder(view).listen{position, type ->
            val item = list.get(position)
            val intent = Intent(parent.context,SearchActivity::class.java)
            intent.putExtra("query",item.title)
            startActivity(parent.context,intent,Bundle.EMPTY)
        }
    }

    override fun onBindViewHolder(holder: categoryViewHolder, position: Int) {
        val data =  list[position]
        holder.category_id.text = data.id
        val urls = data.cover_photo.urls
        Glide.with(context).load(urls.get("thumb")).into(holder.category_img)
        holder.category_text.text = data.title
    }

    override fun getItemCount(): Int {
        return list.size
    }
    fun <T:RecyclerView.ViewHolder> T.listen(event:(position:Int,type:Int)->Unit) :T{
        itemView.setOnClickListener{
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }
}