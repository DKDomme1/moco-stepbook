package com.example.stepbook.training.data

data class WorkoutUnit(
    val exercise: Exercise? = null,
    var sets: Int? = null,
    var reps: Int? = null,
)