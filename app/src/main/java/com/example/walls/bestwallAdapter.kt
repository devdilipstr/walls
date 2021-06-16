package com.example.walls

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.IOException
import java.net.URL


class BestWallAdapter(val list: List<String>) :
    RecyclerView.Adapter<BestWallAdapter.BestWallViewHolder>() {
    class BestWallViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val context = itemView.context
        val best_img: ImageView = itemView.findViewById(R.id.wallthumb_img)
        val url: TextView = itemView.findViewById(R.id.wallthumb_url)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestWallViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.wallthumb, parent, false)
        return BestWallViewHolder(view)
    }

    override fun onBindViewHolder(holder: BestWallViewHolder, position: Int) {
        val url = list[position]
        Glide.with(holder.context).load(url).into(holder.best_img)
        holder.url.text = url
    }

    override fun getItemCount(): Int {
        return list.size
    }

}


