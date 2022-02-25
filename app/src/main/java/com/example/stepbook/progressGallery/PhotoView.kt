package com.example.stepbook.progressGallery

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import com.example.stepbook.R

class PhotoView : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view)

        val path : String = intent.getStringExtra("path").toString()
        val name : String = intent.getStringExtra("name").toString()
        val uri : Uri = (path +"/" + name).toUri()

        //Splittet String in die wichtigen Daten
        val strings : List<String> = name.split(".")

        val imageView : ImageView = findViewById(R.id.photo)
        imageView.setImageURI(uri)

        //nimmt sich aus dem String die wichtigen Daten
        val date : TextView = findViewById(R.id.date)
        val weight : TextView = findViewById(R.id.weight)
        val dateString = strings[0] + "." + strings[1] + "." + strings[2]

        date.text = dateString
        weight.text = strings[3]
    }
}