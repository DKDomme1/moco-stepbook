package com.example.stepbook.training.viewmodels

import androidx.lifecycle.ViewModel
import com.example.stepbook.training.data.WorkoutUnit

class CreateWorkoutViewModel : ViewModel() {
    val workoutUnits = ArrayList<WorkoutUnit>()
}