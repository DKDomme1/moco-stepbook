package com.example.stepbook.training

data class WorkoutPlan(
    val id:Int,
    val workout_units: Array<WorkoutUnit>,
    val title:String,
    val description:String
    ){
    companion object FirestoreKeys{
        val ID_KEY = "id"
        val WORKOUT_UNITS_KEY = "workout_units"
        val TITLE_KEY = "title"
        val DESCRIPTION_KEY = "description"
    }
}