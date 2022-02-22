package com.example.stepbook.training

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.stepbook.R

class PublicWorkoutsFragment : Fragment(R.layout.training_public_workouts){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model:PublicWorkoutsViewModel by viewModels()
        model.getLiveData().observe(this, Observer<List<WorkoutPlan>> {
            //TODO do this
        })
    }
}