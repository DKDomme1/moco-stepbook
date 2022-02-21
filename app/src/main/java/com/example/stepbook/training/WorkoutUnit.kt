package com.example.stepbook.training

data class WorkoutUnit(
    val exercise:Exercise,
    val sets:Int,
    val reps:Int,
    val use_minutes: Boolean,
    val hint:String
    ){
}