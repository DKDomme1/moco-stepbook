package com.example.stepbook

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

import androidx.core.net.toUri

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.reflect.Array.get
import java.net.URI
import java.nio.channels.FileChannel

import java.util.*

class ChooseWeight : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_weight)

        val uri : Uri? = intent.getStringExtra("Foto")?.toUri()

        val imageView : ImageView = findViewById(R.id.progress_Picture)

        imageView.setImageURI(uri)

        val button : Button = findViewById(R.id.accept_photo_button)

        val kiloText : EditText = findViewById(R.id.weight_input)


        button.setOnClickListener {
            if(kiloText.text.toString().toIntOrNull() == null){
                val toastNull = Toast.makeText(this, "Bitte gib ein Gewicht an!", Toast.LENGTH_SHORT)
                toastNull.show()
            }else{

                val kilo : Int = kiloText.text.toString().toInt()
                val toast = Toast.makeText(this, "Bitte gib ein realistisches Gewicht an! ", Toast.LENGTH_SHORT)
                if(kilo <= 45 || kilo >= 250){
                    toast.show()
                }else{

                    val storeDirectory = this.getExternalFilesDir(Environment.DIRECTORY_DCIM)

                    Log.d(TAG, "onCreate: $storeDirectory")
                    val month : Int = Calendar.getInstance().get(Calendar.MONTH) + 1
                    val dateString : String = (Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).toString() + "." + month.toString() + "." + Calendar.getInstance().get(Calendar.YEAR).toString()

                    val file = File(storeDirectory, dateString + "." + kilo.toString()+".jpg")

                    val stream : OutputStream = FileOutputStream(file)

                    val drawable = imageView.drawable

                    val bitmap = (drawable as BitmapDrawable).bitmap

                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)

                    stream.close()




                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }



        }




    }


}