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
        val exercises = arrayOf<Exercise>(
            Exercise("Name1", "Desc1"),
            Exercise("Name2", "Desc2"),
            Exercise("Name3", "Desc3"),
            Exercise("Name4", "Desc4"),
            Exercise("Name4", "Desc4")
        )
        var workoutUnits: List<WorkoutUnit> = emptyList<WorkoutUnit>()
        for (unit in exercises){
            workoutUnits = workoutUnits + WorkoutUnit(unit, 4,8,false,"")
        }
        val workout_plan = WorkoutPlan(workoutUnits,"Workout A", "");
        FirebaseFirestore.getInstance().collection("workouts").add(workout_plan)
    }

    fun getLiveData() : LiveData<List<WorkoutPlan>>{
        fetchData()
        return workouts
    }

    private fun fetchData() {
        //refetch everytime (for now...)
        workouts.value = emptyList()
        //TODO exception handling ...
        FirebaseFirestore.getInstance()
            .collection("workouts")
            .get()
            .addOnSuccessListener { it ->
                if(it != null){
                    for (document in it){
                        val fetched_obj = document.toObject(WorkoutPlan::class.java)
                        workouts.value=workouts.value!! + fetched_obj
                        Log.d(TAG, "added "+ fetched_obj.toString())
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