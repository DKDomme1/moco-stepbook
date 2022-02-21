package com.example.stepbook.training

data class WorkoutUnit(
    val exercise:Exercise,
    val sets:Int,
    val reps:Int,
    val use_minutes: Boolean,
    val hint:String
    ){
    companion object FirestoreKeys{
        val EXERCISE_KEY = "exercise"
        val SETS_KEY = "sets"
        val REPS_KEY = "reps"
        val USE_MINUTES_KEY = "use_minutes"
        val HINT_KEY = "hint"
    }
}