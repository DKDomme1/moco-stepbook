package com.example.stepbook.common

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stepbook.training.data.Exercise
import com.example.stepbook.training.data.WorkoutPlan
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreViewModel : ViewModel() {
    private val TAG = "FirestoreViewModel"
    private val publicWorkouts: MutableLiveData<List<WorkoutPlan>> =
        MutableLiveData<List<WorkoutPlan>>()
    private val publicExercises: MutableLiveData<List<Exercise>> = MutableLiveData<List<Exercise>>()
    private val users: MutableLiveData<List<User>> = MutableLiveData<List<User>>()
    private val myWorkouts: MutableLiveData<List<WorkoutPlan>> =
        MutableLiveData<List<WorkoutPlan>>()

    init {
        publicWorkouts.value = emptyList()
        publicExercises.value = emptyList()
        users.value = emptyList()
    }


    fun getPublicWorkouts() : LiveData<List<WorkoutPlan>> {
        return publicWorkouts
    }
    fun getPublicExercises() : LiveData<List<Exercise>> {
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
                        exercises.add(exercise)
                    }
                }
                publicExercises.value = exercises.toList()
            }.addOnFailureListener {
                Log.d(TAG, "Exercise Failure: $it")
            }
    }
    fun getWorkoutById(id:String): Task<DocumentSnapshot> {
        return FirebaseFirestore.getInstance().collection("workouts").document(id).get()
    }
    fun getExerciseById(id:String): Task<DocumentSnapshot>{
        return FirebaseFirestore.getInstance().collection("exercises").document(id).get()
    }
    fun getUserById(id:String): Task<DocumentSnapshot>{
        return FirebaseFirestore.getInstance().collection("users").document(id).get()
    }
}