package com.example.stepbook.common

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.ktx.Firebase

class FirestoreUtil{
    companion object{
        private val USERS_COLLECTION = "users"
        private val WORKOUTS_COLLECTION = "workouts"
        private val EXERCISES_COLLECTION = "exercises"

        private val TAG = "FirestoreUtil"

        fun fetchPublicWorkouts() : Task<QuerySnapshot> {
            return FirebaseFirestore.getInstance().collection(WORKOUTS_COLLECTION).get()
        }
        fun fetchUserWorkouts() : Task<QuerySnapshot>{
            return FirebaseFirestore.getInstance()
                .collection(USERS_COLLECTION)
                .document(Firebase.auth.currentUser!!.uid)
                .collection(WORKOUTS_COLLECTION)
                .get()
        }
        fun fetchPublicExercises() : Task<QuerySnapshot> {
            return FirebaseFirestore.getInstance().collection(EXERCISES_COLLECTION)
                .get()
        }
        fun getPublicWorkoutById(id:String): Task<DocumentSnapshot> {
            return FirebaseFirestore.getInstance().collection(WORKOUTS_COLLECTION).document(id).get()
        }
        fun getUserWorkoutById(id:String): Task<DocumentSnapshot> {
            return FirebaseFirestore.getInstance()
                .collection(USERS_COLLECTION)
                .document(Firebase.auth.currentUser!!.uid)
                .collection(WORKOUTS_COLLECTION)
                .document(id).get()
        }
        fun getExerciseById(id:String): Task<DocumentSnapshot>{
            return FirebaseFirestore.getInstance().collection(EXERCISES_COLLECTION).document(id).get()
        }
        fun getUserById(id:String): Task<DocumentSnapshot>{
            return FirebaseFirestore.getInstance().collection(USERS_COLLECTION).document(id).get()
        }
    }
}