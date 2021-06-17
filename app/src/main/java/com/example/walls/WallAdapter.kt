package com.example.walls

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import retrofit2.Callback


@Suppress("DEPRECATION")
class WallAdapter(val context: Context, val list:MutableList<Image>, val layout:Int) :
    RecyclerView.Adapter<WallAdapter.WallViewHolder>() {
    class WallViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val best_img: ImageView = itemView.findViewById(R.id.wallthumb_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(layout, parent, false)
        return WallViewHolder(view).listen{position, type ->
            val item = list[position]
            val intent = Intent(parent.context,IndividualImg::class.java)
            intent.putExtra("id",item.id)
            intent.putExtra("url", item.urls.get("regular").toString())
            startActivity(parent.context,intent, Bundle.EMPTY)
        }
    }

    override fun onBindViewHolder(holder: WallViewHolder, position: Int) {
        val item = list[position]
        val url = item.urls.get("thumb")
        Glide.with(context).load(url).into(holder.best_img)
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


