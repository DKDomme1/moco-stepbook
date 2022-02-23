package com.example.stepbook

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import com.example.stepbook.overview.FitPhoto
import java.time.LocalDate
import java.util.*

class ChooseWeight : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_weight)

        val uri : Uri? = intent.getStringExtra("Foto")?.toUri()

        val imageView : ImageView = findViewById(R.id.progress_Picture) // geht

        imageView.setImageURI(uri) // geht, zeigt bild nicht

        val button : Button = findViewById(R.id.accept_photo_button) // geht

        val kiloText : EditText = findViewById(R.id.weight_input) // geht


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



                    val foto : FitPhoto = FitPhoto(kilo, uri.toString(), Calendar.getInstance().toString())




                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }



        }




    }
}