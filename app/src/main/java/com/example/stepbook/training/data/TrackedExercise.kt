package com.example.stepbook.training.data

data class TrackedExercise(
    var docId: String? = null,
    var exerciseDocId: String? = null,
    var datapoints: HashMap<String, Int>? = null
)