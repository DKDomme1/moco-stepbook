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
import com.example.stepbook.training.data.Exercise
import com.example.stepbook.training.fragments.PublicExercisesFragmentDirections

class PublicExercisesAdapter(val publicExercises: List<Exercise>)
    : RecyclerView.Adapter<PublicExercisesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var exercise: Exercise? = null
        val exerciseName: TextView = itemView.findViewById(R.id.exercise_title)
        val exerciseImage: ImageView = itemView.findViewById(R.id.exercise_image)
        val viewExerciseButton: Button = itemView.findViewById(R.id.view_exercise)

        fun setData(exercise: Exercise){
            this.exercise = exercise

            //TODO get exercise image and set it here
            exerciseImage.setImageResource(R.drawable.placeholder)
            exerciseName.text = exercise.name

            viewExerciseButton.setOnClickListener {
                val action = PublicExercisesFragmentDirections
                    .actionPublicExercisesFragmentToViewExerciseFragment(exercise.docId!!)
                itemView.findNavController().navigate(action)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.public_exercises_list_item, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(publicExercises[position].copy())
    }

    override fun getItemCount(): Int {
        return publicExercises.size
    }
}
