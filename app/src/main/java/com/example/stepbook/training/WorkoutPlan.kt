package com.example.stepbook.training

data class WorkoutPlan(
    val id:Int,
    val exercises: Array<Exercise>,
    val description:String
) {
}