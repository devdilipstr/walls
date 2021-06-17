package com.example.walls

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class IndividualImg:AppCompatActivity(){
    lateinit var Image_Imageview:ImageView
    lateinit var Back_Imagebtn:ImageButton
    lateinit var Download_Imagebtn:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image)
        Image_Imageview =  findViewById(R.id.image)
        Back_Imagebtn = findViewById(R.id.back_btn)
        Download_Imagebtn= findViewById(R.id.download_btn)


        val intent = intent
        val url  = intent.getStringExtra("url")

        Glide.with(this).load(url).placeholder(R.drawable.background_color).into(Image_Imageview)


    }
}