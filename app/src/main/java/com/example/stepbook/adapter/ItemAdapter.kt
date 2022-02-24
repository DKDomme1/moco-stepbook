package com.example.stepbook.adapter

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.stepbook.R
import com.example.stepbook.data.PhotoInformations
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ItemAdapter(var pathList : ArrayList<PhotoInformations>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    var pathsList : ArrayList<PhotoInformations> = pathList



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout, parent, false)



        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val uriString: String = pathsList[position].path + "/" + pathsList[position].name

        holder.imageView.setImageURI(uriString.toUri())
    }

    override fun getItemCount(): Int {
        return pathsList.size

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.item_image)

        init {
            imageView.setOnClickListener {

            }
        }

    }
}