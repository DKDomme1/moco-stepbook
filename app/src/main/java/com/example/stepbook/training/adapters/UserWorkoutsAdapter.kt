package com.example.stepbook.training.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.stepbook.R
import com.example.stepbook.common.FirestoreUtil
import com.example.stepbook.training.data.WorkoutPlan
import com.example.stepbook.training.fragments.UserWorkoutsFragmentDirections

class UserWorkoutsAdapter(private val userWorkouts: List<WorkoutPlan>)
    : RecyclerView.Adapter<UserWorkoutsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var workoutPlan: WorkoutPlan? = null
        val workoutName: TextView = itemView.findViewById(R.id.workout_title)
        val workoutImage: ImageView = itemView.findViewById(R.id.workout_image)
        val viewWorkoutButton: Button = itemView.findViewById(R.id.view_workout)
        val removeWorkoutButton: Button = itemView.findViewById(R.id.remove_workout)

        fun setData(workoutPlan: WorkoutPlan){
            this.workoutPlan = workoutPlan
            //TODO get workout image and set it here
            workoutImage.setImageResource(R.drawable.placeholder)
            workoutName.setText(workoutPlan.title)

            viewWorkoutButton.setOnClickListener {
                val action = UserWorkoutsFragmentDirections
                    .actionUserWorkoutsFragmentToViewWorkoutFragment(workoutPlan.docId!!, false)
                itemView.findNavController().navigate(action)
            }

            removeWorkoutButton.setOnClickListener {
                FirestoreUtil.removeUserWorkout(workoutPlan.docId!!).addOnCompleteListener {
                    if (it.isSuccessful){
                        removeWorkoutButton.isEnabled = false
                        Toast.makeText(
                            itemView.context,
                            "Workout has been removed from your List",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        Toast.makeText(
                            itemView.context,
                            it.exception!!.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
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
