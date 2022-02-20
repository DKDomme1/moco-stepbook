package com.example.stepbook.data

import androidx.lifecycle.LiveData
import com.example.stepbook.R
import com.example.stepbook.overview.FitPhoto

class Datasource {

    val photos : MutableList<FitPhoto>
        get() {
            return this.photos
        }


    fun addPhoto( photo : FitPhoto){
        photos.add(photo)
    }

    fun loadPhotos(): MutableList<FitPhoto> {
        return this.photos
    }


}