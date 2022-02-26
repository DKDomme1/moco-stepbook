package com.example.stepbook

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.stepbook.databinding.ActivityMainBinding
import com.example.stepbook.training.data.Exercise
import com.example.stepbook.training.data.WorkoutPlan
import com.example.stepbook.training.data.WorkoutUnit
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private val USE_FIREBASE_EMULATOR = true
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            if (USE_FIREBASE_EMULATOR) {
                FirebaseFirestore.getInstance().useEmulator("10.0.2.2", 8080)
                FirebaseAuth.getInstance().useEmulator("10.0.2.2", 9099)
            }
        }


        val ex = listOf<Exercise>(
            Exercise(null, "Ex1", "Desc1"),
            Exercise(null, "Ex2", "Desc2"),
            Exercise(null, "Ex3", "Desc3"),
            Exercise(null, "Ex4", "Desc4"),
            Exercise(null, "Ex5", "Desc5"),
        )
        var woUnits = emptyList<WorkoutUnit>()
        for (exer in ex) {
            val docRef = FirebaseFirestore.getInstance().collection("exercises").document()
            exer.docId = docRef.id
            docRef.set(exer)
            woUnits = woUnits + WorkoutUnit(exer, 4, 8)
            Log.d(TAG, docRef.id)
        }
        Log.d(TAG, woUnits.toString())
        val docRef = FirebaseFirestore.getInstance().collection("workouts").document()
        val woPlan = WorkoutPlan(docRef.id, null, woUnits, "Wo1", "Desc1", true)
        docRef.set(woPlan)
        //firestore.collection("workouts").add(woPlan)
    }
}