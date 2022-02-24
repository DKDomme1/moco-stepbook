package com.example.stepbook.common

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stepbook.training.data.Exercise
import com.example.stepbook.training.data.User
import com.example.stepbook.training.data.WorkoutPlan
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreViewModel : ViewModel() {
    private val TAG = "FirestoreViewModel"
    private val publicWorkouts: MutableLiveData<List<WorkoutPlan>> =
        MutableLiveData<List<WorkoutPlan>>()
    private val publicExercises: MutableLiveData<List<Exercise>> = MutableLiveData<List<Exercise>>()
    private val users: MutableLiveData<List<User>> = MutableLiveData<List<User>>()
    //TODO
    private val myWorkouts: MutableLiveData<List<WorkoutPlan>> =
        MutableLiveData<List<WorkoutPlan>>()
    private val myExercises: MutableLiveData<List<WorkoutPlan>> =
        MutableLiveData<List<WorkoutPlan>>()

    init {
        publicWorkouts.value = emptyList()
        publicExercises.value = emptyList()
        users.value = emptyList()
    }


    fun getPublicWorkouts() : LiveData<List<WorkoutPlan>> {
        return publicWorkouts
    }
    fun getExercises() : LiveData<List<Exercise>> {
        return publicExercises
    }
    fun getUsers() : LiveData<List<User>> {
        return users
    }

    //TODO exception handling
    fun fetchPublicWorkouts() {
        FirebaseFirestore.getInstance().collection("workouts")
            .get()
            .addOnSuccessListener {
                Log.d(TAG, "Workouts Success: $it\nDocuments: ${it.documents}")
                val workouts = ArrayList<WorkoutPlan>()
                for (document in it.documents){
                    val workout = document.toObject(WorkoutPlan::class.java)
                    if (workout != null) {
                        workout.docId = document.id
                        workouts.add(workout)
                    }
                }
                publicWorkouts.value = workouts.toList()
            }.addOnFailureListener {
                Log.d(TAG, "Workouts Failure: $it")
            }
    }
    fun fetchPublicExercises() {
        FirebaseFirestore.getInstance().collection("exercises")
            .get()
            .addOnSuccessListener {
                val exercises = ArrayList<Exercise>()
                for (document in it.documents){
                    Log.d(TAG, "Exercise Success: $it \nDocuments: ${it.documents}")
                    val exercise = document.toObject(Exercise::class.java)
                    if (exercise != null) {
                        exercise.docId = document.id
                        exercises.add(exercise)
                    }
                }
                publicExercises.value = exercises.toList()
            }.addOnFailureListener {
                Log.d(TAG, "Exercise Failure: $it")
            }
    }
    fun getWorkoutById(id:String): WorkoutPlan?{
        for (workout in publicWorkouts.value!!){
            if (workout.docId == id) return workout
        }
        return null
    }
    fun getExerciseById(id:String): Exercise?{
        for (exercise in publicExercises.value!!){
            if (exercise.docId == id) return exercise
        }
        return null
    }
    fun getUserById(id:String): User?{
        for (user in users.value!!){
            if (user.docId == id) return user
        }
        return null
    }
}