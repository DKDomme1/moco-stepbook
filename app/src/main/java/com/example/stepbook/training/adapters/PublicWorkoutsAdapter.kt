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
import com.example.stepbook.training.data.WorkoutPlan
import com.example.stepbook.training.fragments.PublicWorkoutsFragmentDirections
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class PublicWorkoutsAdapter(
    private val publicWorkouts: List<WorkoutPlan>,
    val userWorkouts: List<WorkoutPlan>?)
    : RecyclerView.Adapter<PublicWorkoutsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var workoutPlan: WorkoutPlan? = null
        val workoutName:TextView = itemView.findViewById(R.id.workout_title)
        val workoutImage:ImageView = itemView.findViewById(R.id.workout_image)
        val viewWorkoutButton:Button = itemView.findViewById(R.id.view_workout)
        val addWorkoutToList:Button = itemView.findViewById(R.id.add_workout_to_list)

        fun setData(workoutPlan: WorkoutPlan, userWorkouts: List<WorkoutPlan>?){
            this.workoutPlan = workoutPlan

            //TODO get workout image and set it here
            workoutImage.setImageResource(R.drawable.placeholder)
            workoutName.setText(workoutPlan.title)

            var alreadyInList = false
            if (userWorkouts != null){
                for (workout in userWorkouts)
                    if(workout.docId == workoutPlan.docId) alreadyInList = true
            }

            addWorkoutToList.isEnabled = !alreadyInList && userWorkouts != null

            viewWorkoutButton.setOnClickListener {
                val action =PublicWorkoutsFragmentDirections
                    .actionPublicWorkoutsFragmentToViewWorkoutFragment(workoutPlan.docId!!)
                itemView.findNavController().navigate(action)
            }
            if(addWorkoutToList.isEnabled) {
                addWorkoutToList.setOnClickListener {
                    addWorkoutToList.isEnabled = false
                    FirebaseFirestore.getInstance()
                        .collection("users")
                        .document(Firebase.auth.currentUser!!.uid)
                        .collection("workouts")
                        .add(workoutPlan)
                        .addOnSuccessListener {

                            Toast.makeText(
                                itemView.context, "Workout has been added to your list !",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(
                                itemView.context, it.message,
                                Toast.LENGTH_SHORT
                            ).show()
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
        holder.setData(publicWorkouts[position].copy(), userWorkouts)
    }

    override fun getItemCount(): Int {
        return publicWorkouts.size
    }
}