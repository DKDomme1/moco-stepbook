package com.example.stepbook.training

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class PublicWorkoutsViewModel : ViewModel() {
    private val workouts:MutableLiveData<List<WorkoutPlan>> = MutableLiveData<List<WorkoutPlan>>()

    fun getLiveData() : LiveData<List<WorkoutPlan>>{
        fetchData()
        return workouts
    }

    private fun fetchData() {
        //TODO exception handling ...
        workouts.value = emptyList()
        FirebaseFirestore.getInstance()
            .collection("workouts")
            .get()
            .addOnSuccessListener { it ->
                if(it != null){
                    for (document in it){
                        val fetched_obj = document.toObject(WorkoutPlan::class.java)
                        fetched_obj.docId = document.id
                        workouts.value=workouts.value!! + fetched_obj
                    }
                } else {
                    Log.d(TAG, "no such collection")
                }
            }
            .addOnFailureListener { it ->
                Log.d(TAG, "get failed with ", it)
            }
    }
}