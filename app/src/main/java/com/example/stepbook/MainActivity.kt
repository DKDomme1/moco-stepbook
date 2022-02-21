package com.example.stepbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

import com.example.stepbook.adapter.ItemAdapter
import com.example.stepbook.data.Datasource

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myDataset = Datasource().loadPhotos()

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)

        recyclerView.adapter = ItemAdapter(this, myDataset)

        recyclerView.setHasFixedSize(false)

        val toCamButton: Button = findViewById(R.id.add_photo)

        toCamButton.setOnClickListener {
            val intent = Intent(this, AddPhoto::class.java)
            startActivity(intent)
        }


    }
}
