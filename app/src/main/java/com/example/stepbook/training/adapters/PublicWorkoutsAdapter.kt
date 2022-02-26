package com.example.stepbook.training.adapters

import android.util.Log
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
import com.example.stepbook.training.fragments.PublicWorkoutsFragmentDirections

class PublicWorkoutsAdapter(
    private val publicWorkouts: List<WorkoutPlan>,
    val userWorkouts: List<WorkoutPlan>)
    : RecyclerView.Adapter<PublicWorkoutsAdapter.ViewHolder>() {

    private val TAG = "PublicWorkoutsAdapter"

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val TAG = "ViewHolder"
        var workoutPlan: WorkoutPlan? = null
        val workoutName:TextView = itemView.findViewById(R.id.workout_title)
        val workoutImage:ImageView = itemView.findViewById(R.id.workout_image)
        val viewWorkoutButton:Button = itemView.findViewById(R.id.view_workout)
        val addWorkoutToList:Button = itemView.findViewById(R.id.add_workout_to_list)

        fun setData(workoutPlan: WorkoutPlan, userWorkouts: List<WorkoutPlan>){
            this.workoutPlan = workoutPlan

            //TODO get workout image and set it here
            workoutImage.setImageResource(R.drawable.placeholder)
            workoutName.text = workoutPlan.title

            var alreadyInList = false
            for (userWorkout in userWorkouts){
                if (workoutPlan.docId == userWorkout.publicDocId){
                    alreadyInList = true
                    break
                }
            }
            addWorkoutToList.isEnabled = !alreadyInList

            viewWorkoutButton.setOnClickListener {
                val action =PublicWorkoutsFragmentDirections
                    .actionPublicWorkoutsFragmentToViewWorkoutFragment(workoutPlan.docId!!,  true)
                itemView.findNavController().navigate(action)
            }
            if(addWorkoutToList.isEnabled) {
                addWorkoutToList.setOnClickListener {
                    addWorkoutToList.isEnabled = false
                    FirestoreUtil.addPublicWorkoutToUserWorkouts(workoutPlan.docId!!).addOnCompleteListener {
                        if (it.isSuccessful){
                            Log.d(TAG, "Successfully added to list")
                            Toast.makeText(
                                itemView.context, "Workout has been added to your list !",
                            Toast.LENGTH_SHORT
                            ).show()
                        }else{
                            Log.d(TAG, it.exception?.message!!)
                            Toast.makeText(
                                itemView.context, it.exception!!.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.public_workouts_list_item, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(publicWorkouts[position], userWorkouts)
    }

    override fun getItemCount(): Int {
        return publicWorkouts.size
    }
}