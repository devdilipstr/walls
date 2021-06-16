package com.example.walls

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.w3c.dom.Text

class categoryAdapter(val context:Context,val list:List<category>): RecyclerView.Adapter<categoryAdapter.categoryViewHolder>() {
    class categoryViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val category_id = itemView.findViewById<TextView>(R.id.category_id)
        val category_img= itemView.findViewById<ImageView>(R.id.category_img)
        val category_text = itemView.findViewById<TextView>(R.id.category_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): categoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view:View = inflater.inflate(R.layout.category_layout,parent,false)
        return categoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: categoryViewHolder, position: Int) {
        val data =  list[position]
        holder.category_id.text = data.id
        Glide.with(context).load(data.imgurl).into(holder.category_img)
        holder.category_text.text = data.name
    }

    override fun getItemCount(): Int {
        return list.size
    }

}