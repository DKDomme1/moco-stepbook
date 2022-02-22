package com.example.stepbook.training

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class PublicWorkoutsViewModel : ViewModel() {
    private val workouts:MutableLiveData<List<WorkoutPlan>> = MutableLiveData<List<WorkoutPlan>>()
    //TODO remove this
    init {
        arrayOf<Exercise>(Exercise("Name1", "Desc1")
        Exercise("Name2", "Desc2")
        Exercise("Name3", "Desc3")
        Exercise("Name4", "Desc4")
        Exercise("Name4", "Desc4")
    }

    fun getLiveData() : LiveData<List<WorkoutPlan>>{
        fetchData()
        return workouts
    }

    private fun fetchData() {
        //TODO refetch everytime (for now...)
        workouts.value = emptyList()
        FirebaseFirestore.getInstance()
            .collection("workouts")
            .get()
            .addOnSuccessListener { it ->
                /*if(it != null){
                    Log.d(TAG, "CollectionSnapshot data: ${it.documents}")
                } else {
                    Log.d(TAG, "no such collection")
                }*/
                for (document in it){
                    Log.d(TAG, "data: ${document.toObject<WorkoutPlan>()}")
                }
            }
            .addOnFailureListener { it ->
                Log.d(TAG, "get failed with ", it)
            }
    }
}