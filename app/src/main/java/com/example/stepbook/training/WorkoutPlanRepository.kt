package com.example.stepbook.training

import com.example.stepbook.common.Repository

class WorkoutPlanRepository : Repository<WorkoutPlan>{
    var workout_plans:Array<WorkoutPlan> = arrayOf<WorkoutPlan>()

    override fun refresh(): Boolean {
        TODO("Not yet implemented")
    }

    override fun size(): Int {
        return workout_plans.size
    }

    override fun get(position: Int): WorkoutPlan {
        return workout_plans.get(position)
    }

    override fun getAll(): Array<WorkoutPlan> {
        return workout_plans.clone()
    }
}
