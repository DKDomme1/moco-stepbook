package com.example.stepbook.training.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.example.stepbook.R
import com.example.stepbook.training.data.WorkoutUnit

class CreateWorkoutAdapter(val workoutUnits: ArrayList<WorkoutUnit>) :
    RecyclerView.Adapter<CreateWorkoutAdapter.ViewHolder>() {
    private val TAG = "CreateWorkoutAdapter"

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val exerciseName: TextView = itemView.findViewById(R.id.exercise_name)
        val sets: TextView = itemView.findViewById(R.id.sets)
        val reps: TextView = itemView.findViewById(R.id.reps)
        val removeExerciseBtn: Button = itemView.findViewById(R.id.remove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.create_workout_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val workoutUnit = workoutUnits[position]
        holder.exerciseName.text = workoutUnit.exercise!!.name
        holder.sets.text = workoutUnit.sets!!.toString()
        holder.reps.text = workoutUnit.reps!!.toString()
        holder.removeExerciseBtn.setOnClickListener {
            workoutUnits.removeAt(holder.adapterPosition)
            notifyItemRemoved(holder.adapterPosition)
        }
        holder.sets.addTextChangedListener {
            if (!it.isNullOrBlank()) {
                workoutUnits[holder.adapterPosition].sets = it.toString().toInt()
            }
        }
        holder.reps.addTextChangedListener {
            if (!it.isNullOrBlank()) {
                workoutUnits[holder.adapterPosition].reps = it.toString().toInt()
            }
        }
    }

    override fun getItemCount(): Int {
        return workoutUnits.size
    }
}
