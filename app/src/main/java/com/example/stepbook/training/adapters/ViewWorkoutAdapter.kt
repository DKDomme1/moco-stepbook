package com.example.stepbook.training.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.stepbook.R
import com.example.stepbook.training.data.WorkoutPlan
import com.example.stepbook.training.data.WorkoutUnit
import com.example.stepbook.training.fragments.ViewWorkoutFragmentDirections

class ViewWorkoutAdapter(val workout: WorkoutPlan) :
    RecyclerView.Adapter<ViewWorkoutAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var workoutUnit: WorkoutUnit? = null
        val exerciseName: TextView = itemView.findViewById(R.id.exercise_name)
        val sets: TextView = itemView.findViewById(R.id.sets)
        val reps: TextView = itemView.findViewById(R.id.reps)
        val viewExerciseBtn: Button = itemView.findViewById(R.id.view_exercise)

        fun setData(workoutUnit: WorkoutUnit) {
            this.workoutUnit = workoutUnit
            exerciseName.text = workoutUnit.exercise!!.name
            sets.text = workoutUnit.sets!!.toString()
            reps.text = workoutUnit.reps!!.toString()
            viewExerciseBtn.setOnClickListener {
                val action = ViewWorkoutFragmentDirections
                    .actionViewWorkoutFragmentToViewExerciseFragment(workoutUnit.exercise.docId!!)
                itemView.findNavController().navigate(action)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_workout_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(workout.workout_units!![position].copy())
    }

    override fun getItemCount(): Int {
        if (workout.workout_units != null) {
            return workout.workout_units.size
        } else {
            return 0
        }
    }
}