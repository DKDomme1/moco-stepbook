package com.example.stepbook.progressGallery

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stepbook.R

import com.example.stepbook.progressGallery.adapter.ItemAdapter
import com.example.stepbook.progressGallery.data.PhotoInformations
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*

class ProgressGallery : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter : RecyclerView.Adapter<ItemAdapter.ViewHolder>? = null


    var pathsList : ArrayList<PhotoInformations>? = null
    val LIST_KEY : String = "list_key"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        readList()



        layoutManager =  GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        adapter = ItemAdapter(pathsList!!)
        recyclerView.adapter = adapter
        (adapter as ItemAdapter).setOnItemClickListener(object : ItemAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
               val intentP = Intent(this@ProgressGallery, PhotoView::class.java)
                intentP.putExtra("path", pathsList!![position].path )
                intentP.putExtra("name", pathsList!![position].name)
                startActivity(intentP)
            }

        })

        val toCamButton: Button = findViewById(R.id.add_photo)

        toCamButton.setOnClickListener {
            val intent = Intent(this, AddPhoto::class.java)
            startActivity(intent)
        }


    }


    //Wieder Liste aus sharedPreferences lesen
    fun readList(){
        val sharedPreferencesLoad : SharedPreferences = getSharedPreferences("shared preferences",
            AppCompatActivity.MODE_PRIVATE
        )
        val gsonLoad : Gson = Gson()
        val jsonLoad : String? = sharedPreferencesLoad.getString(LIST_KEY, null)
        val type = object : TypeToken<ArrayList<PhotoInformations?>?>() {}.type
        pathsList = gsonLoad.fromJson(jsonLoad, type)

        if(pathsList == null){
            pathsList = ArrayList()
        }
    }
}
