package com.example.walls

import android.R.attr
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.io.File
import java.io.FileOutputStream


@Suppress("DEPRECATION")
class IndividualImg : AppCompatActivity() {
    lateinit var Image_Imageview: ImageView
    lateinit var Back_Imagebtn: ImageButton
    lateinit var Download_Imagebtn: ImageButton
    lateinit var Set_Imagebtn: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image)
        Image_Imageview = findViewById(R.id.image)
        Back_Imagebtn = findViewById(R.id.back_btn)
        Download_Imagebtn = findViewById(R.id.download_btn)
        Set_Imagebtn = findViewById(R.id.set_btn)

        val intent = intent
        val url = intent.getStringExtra("url")
        val id = intent.getStringExtra("id")
        Glide.with(this).load(url).placeholder(R.drawable.background_color).into(Image_Imageview)

        Back_Imagebtn.setOnClickListener {
            onBackPressed()
        }

        Download_Imagebtn.setOnClickListener {
            downloadimage(url.toString(),id.toString())
        }
        Set_Imagebtn.setOnClickListener {

        }
    }

    private fun downloadimage(imgurl:String,id:String) {
        if(!verifyPerimssion()) {
            return
        }
        val dirpath = Environment.getExternalStorageDirectory().absolutePath + "/"+ getString(R.string.app_name) + "/"
        val dir:File = File(dirpath)
        val filename = id

        val into = Glide.with(this).load(imgurl).into(object : CustomTarget<Drawable?>() {
            override fun onResourceReady(
                resource: Drawable,
                transition: Transition<in Drawable?>?
            ) {
                val bitmap = resource.toBitmap()
                SaveImage(bitmap,dir,filename)
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }

        })
    }
    private fun SaveImage(resource:Bitmap, dir:File, filename:String) {
        var successdircreated = false
        if(!dir.exists()) {
            successdircreated = dir.mkdir()
            Log.e("failed", "directory created$successdircreated")
        }
        if(successdircreated) {
            val imagefile = File(dir, filename)
            val savedimagepath = imagefile.absoluteFile
            try {
                val fout = FileOutputStream(imagefile)
                resource.compress(Bitmap.CompressFormat.JPEG, 100, fout)
                fout.close()
                Toast.makeText(this,"succesfully downloaded to ${savedimagepath}",Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.e("failed", e.toString())
            }
        }else {
            Log.i("fail","failed")
        }
    }
    private fun verifyPerimssion():Boolean{
        val permissionexternalmemory = ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        if (permissionexternalmemory != PackageManager.PERMISSION_GRANTED) {
            val STORAGE_PERMISSION =
                arrayOf<String>(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            ActivityCompat.requestPermissions(this, STORAGE_PERMISSION, 1)
            return false
        }
        return true
    }

}
