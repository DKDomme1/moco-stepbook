package com.example.stepbook.training

import com.example.stepbook.common.Repository

class ExerciseRepository : Repository<Exercise>{
    var exercises:Array<Exercise> = arrayOf<Exercise>()

    override fun refresh(): Boolean {
        TODO("Not yet implemented")
    }

    override fun size(): Int {
        return exercises.size
    }

    override fun get(position: Int): Exercise {
        return exercises.get(position)
    }

    override fun getAll(): Array<Exercise> {
        return exercises.clone()
    }
}