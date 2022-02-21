package com.example.stepbook.training

data class WorkoutPlan(
    val id:Int,
    val workout_units: Array<WorkoutUnit>,
    val title:String,
    val description:String
) {
}