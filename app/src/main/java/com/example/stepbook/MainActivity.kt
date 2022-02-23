package com.example.stepbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.stepbook.databinding.ActivityMainBinding
import com.example.stepbook.training.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private val USE_FIREBASE_EMULATOR = true

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firestore = FirebaseFirestore.getInstance()
        val auth = FirebaseAuth.getInstance()

        if (USE_FIREBASE_EMULATOR){
            firestore.useEmulator("10.0.2.2", 8080)
            auth.useEmulator("10.0.2.2", 9099)
        }
        val ex = listOf<Exercise>(
            Exercise(null, "Ex1", "Desc1"),
            Exercise(null, "Ex1", "Desc1"),
            Exercise(null, "Ex1", "Desc1"),
            Exercise(null, "Ex1", "Desc1"),
            Exercise(null, "Ex1", "Desc1"),
        )
        var woUnits = emptyList<WorkoutUnit>()
        for (exer in ex){
            woUnits = woUnits + WorkoutUnit(exer, 4,8,"hint")
        }
        val woPlan = WorkoutPlan(null,woUnits,"Wo1", "Desc1")

        if (savedInstanceState == null){
            supportFragmentManager.commit {
                val frament = ViewWorkoutFragment(woPlan)
                setReorderingAllowed(true)
                add(R.id.top_fragment_container, frament)
            }
        }
    }
}