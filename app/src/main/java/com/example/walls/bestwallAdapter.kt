package com.example.walls

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import java.io.IOException
import java.net.URL


class WallAdapter(val list:MutableList<Image>) :
    RecyclerView.Adapter<WallAdapter.WallViewHolder>() {
    class WallViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context = itemView.context
        val best_img: ImageView = itemView.findViewById(R.id.wallthumb_img)
        val url: TextView = itemView.findViewById(R.id.wallthumb_url)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.wallthumb, parent, false)
        return WallViewHolder(view).listen{position, type ->
            val item = list[position]
            val intent = Intent(parent.context,IndividualImg::class.java)
            intent.putExtra("url", item.urls.get("full").toString())
            startActivity(parent.context,intent, Bundle.EMPTY)
        }
    }

    override fun onBindViewHolder(holder: WallViewHolder, position: Int) {
        val item = list[position]
        val url = item.urls.get("thumb")
        Glide.with(holder.context).load(url).into(holder.best_img)
        holder.url.text = url
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


