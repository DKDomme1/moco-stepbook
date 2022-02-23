package com.example.stepbook.training

data class WorkoutPlan(
    var docId:String? = null,

    val workout_units: List<WorkoutUnit>? = null,
    val title:String? = null,
    val description:String? = null
    )