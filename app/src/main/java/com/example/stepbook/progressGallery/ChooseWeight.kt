package com.example.stepbook.progressGallery

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.SharedPreferences
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
import com.example.stepbook.R
import com.example.stepbook.progressGallery.data.PhotoInformations
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.*

import java.util.*
import kotlin.collections.ArrayList





class ChooseWeight : AppCompatActivity() {

    var pathsList : ArrayList<PhotoInformations>? = null
    val LIST_KEY : String = "list_key"





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

                    // Speicherort für das ImageView und neuen Namen auswählen
                    val storeDirectory = this.getExternalFilesDir(Environment.DIRECTORY_DCIM)
                    Log.d(TAG, "onCreate: $storeDirectory")
                    val month : Int = Calendar.getInstance().get(Calendar.MONTH) + 1
                    val dateString : String = (Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).toString() + "." + month.toString() + "." + Calendar.getInstance().get(Calendar.YEAR).toString()
                    //Name mit Metadata
                    val name : String = dateString + "." + kilo.toString()+".jpg"
                    val file = File(storeDirectory, name )


                    //ImageView Speichern, mit neuem Namen
                    val stream : OutputStream = FileOutputStream(file)
                    val drawable = imageView.drawable
                    val bitmap = (drawable as BitmapDrawable).bitmap
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    stream.close()

                    //ArrayList aus Json Laden und benutzen
                    val sharedPreferencesLoad : SharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
                    val gsonLoad : Gson = Gson()
                    val jsonLoad : String? = sharedPreferencesLoad.getString(LIST_KEY, null)
                    val type = object : TypeToken<ArrayList<PhotoInformations?>?>() {}.type
                    pathsList = gsonLoad.fromJson(jsonLoad, type)
                    if(pathsList == null){
                        pathsList = ArrayList()
                    }

                    //ImageView Pfad und Name zur ArrayList hinzufügen
                    pathsList?.add(PhotoInformations(storeDirectory.toString(), name))

                    //Arraylist speichern
                    val sharedPreferencesSave : SharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE)
                    val editor : SharedPreferences.Editor = sharedPreferencesSave.edit()
                    val gsonSave : Gson = Gson()
                    val jsonSave : String = gsonSave.toJson(pathsList)
                    editor.putString(LIST_KEY, jsonSave)
                    editor.apply()

                    //Intent zurück zur Progress Gallery
                    val intent = Intent(this, ProgressGallery::class.java)
                    startActivity(intent)
                }
            }



        }




    }


}