package com.example.stepbook.training.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.stepbook.R
import com.example.stepbook.training.data.WorkoutPlan
import com.example.stepbook.training.fragments.UserWorkoutsFragmentDirections

class UserWorkoutsAdapter(private val userWorkouts: List<WorkoutPlan>)
    : RecyclerView.Adapter<UserWorkoutsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var workoutPlan: WorkoutPlan? = null
        val workoutName: TextView = itemView.findViewById(R.id.workout_title)
        val workoutImage: ImageView = itemView.findViewById(R.id.workout_image)
        val viewWorkoutButton: Button = itemView.findViewById(R.id.view_workout)

        fun setData(workoutPlan: WorkoutPlan){
            this.workoutPlan = workoutPlan
            //TODO get workout image and set it here
            workoutImage.setImageResource(R.drawable.placeholder)
            workoutName.setText(workoutPlan.title)

            if(workoutPlan.isPublic == null) workoutPlan.isPublic = true

            viewWorkoutButton.setOnClickListener {
                val action = UserWorkoutsFragmentDirections
                    .actionUserWorkoutsFragmentToViewWorkoutFragment(workoutPlan.docId!!, workoutPlan.isPublic!!)
                itemView.findNavController().navigate(action)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_workouts_list_item, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(userWorkouts[position].copy())
    }

    override fun getItemCount(): Int {
        return userWorkouts.size
    }
}
