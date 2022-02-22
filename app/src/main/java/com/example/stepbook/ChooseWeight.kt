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

class ChooseWeight : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_weight)

        val uri : Uri? = intent.getStringExtra("Foto")?.toUri()

        val imageView : ImageView = findViewById(R.id.progress_Picture)

        //imageView.setImageURI(uri) // geht noch nicht?

        //val button : Button = findViewById(R.id.accept_photo_button)

        //val kiloText : EditText = findViewById(R.id.weight_input)
        //val kilo : Int = kiloText.text.toString().toInt()
        val toast = Toast.makeText(this, "Bitte gib ein realistisches Gewicht an!", Toast.LENGTH_SHORT)

        /*button.setOnClickListener {
            //if(kilo <= 40){
                toast.show()
            //}else{
                //speichern, nur wo?


                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            //}
        }*/




    }
}