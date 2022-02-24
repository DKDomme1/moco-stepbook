package com.example.stepbook

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri

class PhotoView : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_view)

        val path : String = intent.getStringExtra("path").toString()
        val name : String = intent.getStringExtra("name").toString()
        val uri : Uri = (path +"/" + name).toUri()

        val strings : List<String> = name.split(".")

        val imageView : ImageView = findViewById(R.id.photo)
        imageView.setImageURI(uri)

        val date : TextView = findViewById(R.id.date)

        val weight : TextView = findViewById(R.id.weight)

        val dateString = strings[0] + "." + strings[1] + "." + strings[2]

        date.text = dateString

        weight.text = strings[3]
    }
}