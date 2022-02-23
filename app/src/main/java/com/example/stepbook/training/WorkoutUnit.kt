package com.example.stepbook.training

data class WorkoutUnit(
    val exercise:Exercise? = null,
    val sets:Int? = null,
    val reps:Int? = null,
    val use_minutes: Boolean? = null,
    val hint:String? = null
    )