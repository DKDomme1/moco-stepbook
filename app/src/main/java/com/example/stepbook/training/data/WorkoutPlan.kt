package com.example.stepbook.training.data

data class WorkoutPlan(
    var docId: String? = null,
    var publicDocId: String? = null,

    val workout_units: List<WorkoutUnit>? = null,
    val title: String? = null,
    val description: String? = null,
    var isPublic: Boolean? = null,
    var ownerUId: String? = null,
)