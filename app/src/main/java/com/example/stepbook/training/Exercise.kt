package com.example.stepbook.training

data class Exercise(
    val id:Int,
    val name:String,
    val description:String
    ){
    companion object FirestoreKeys{
        val ID_KEY = "id"
        val NAME_KEY = "name"
        val DESCRIPTION_KEY = "description"
    }
}