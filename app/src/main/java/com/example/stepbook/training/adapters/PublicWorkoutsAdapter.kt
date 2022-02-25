package com.example.stepbook.training.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.stepbook.R
import com.example.stepbook.training.data.WorkoutPlan
import com.example.stepbook.training.fragments.PublicWorkoutsFragmentDirections

class PublicWorkoutsAdapter(val data: LiveData<List<WorkoutPlan>>)
    : RecyclerView.Adapter<PublicWorkoutsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var workoutPlan: WorkoutPlan? = null
        val workoutName:TextView = itemView.findViewById(R.id.workout_title)
        val workoutImage:ImageView = itemView.findViewById(R.id.workout_image)
        val viewWorkoutButton:Button = itemView.findViewById(R.id.view_workout)
        val addWorkoutToList:Button = itemView.findViewById(R.id.add_workout_to_list)

        fun setData(workoutPlan: WorkoutPlan){
            this.workoutPlan = workoutPlan

            //TODO get workout image and set it here
            workoutImage.setImageResource(R.drawable.placeholder)
            workoutName.setText(workoutPlan.title)

            viewWorkoutButton.setOnClickListener {
                val action =PublicWorkoutsFragmentDirections
                    .actionPublicWorkoutsFragmentToViewWorkoutFragment(workoutPlan.docId!!)
                itemView.findNavController().navigate(action)
            }
            addWorkoutToList.setOnClickListener {
                TODO("add to user list")
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.public_workouts_list_item, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(data.value!![position].copy())
    }

    override fun getItemCount(): Int {
        return data.value!!.size
    }
}