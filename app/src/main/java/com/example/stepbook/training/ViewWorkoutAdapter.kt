package com.example.stepbook.training

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stepbook.R

class ViewWorkoutAdapter(val workoutUnits:List<WorkoutUnit>?) : RecyclerView.Adapter<ViewWorkoutAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var workoutUnit:WorkoutUnit? = null
        val exerciseName:TextView = itemView.findViewById(R.id.exercise_name)
        val sets:TextView = itemView.findViewById(R.id.sets)
        val reps:TextView = itemView.findViewById(R.id.reps)
        val hint:TextView = itemView.findViewById(R.id.workout_unit_hint)
        val viewExerciseBtn: Button = itemView.findViewById(R.id.view_exercise)

        fun setData(workoutUnit: WorkoutUnit) {
            this.workoutUnit = workoutUnit
            exerciseName.setText(workoutUnit.exercise!!.name)
            sets.setText(workoutUnit.sets!!.toString())
            reps.setText(workoutUnit.reps!!.toString())
            hint.setText(workoutUnit.hint!!)
            viewExerciseBtn.setOnClickListener {
                TODO("Go to view "+workoutUnit.exercise.name)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_workout_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(workoutUnits!![position].copy())
    }

    override fun getItemCount(): Int {
        if (workoutUnits !=  null){
            return workoutUnits.size
        } else {
            return 0
        }
    }
}