package com.example.stepbook.training.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.findFragment
import androidx.navigation.NavArgs
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.stepbook.R
import com.example.stepbook.training.data.Exercise
import com.example.stepbook.training.data.WorkoutUnit
import com.example.stepbook.training.fragments.PublicExercisesFragment
import com.example.stepbook.training.fragments.PublicExercisesFragmentArgs
import com.example.stepbook.training.fragments.PublicExercisesFragmentDirections
import com.example.stepbook.training.viewmodels.CreateWorkoutViewModel

class PublicExercisesAdapter(val publicExercises: List<Exercise>, val fragm:PublicExercisesFragment)
    : RecyclerView.Adapter<PublicExercisesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var exercise: Exercise? = null
        val exerciseName: TextView = itemView.findViewById(R.id.exercise_title)
        val exerciseImage: ImageView = itemView.findViewById(R.id.exercise_image)
        val actionButton: Button = itemView.findViewById(R.id.view_exercise)

        fun setData(exercise: Exercise, fragm: PublicExercisesFragment){
            this.exercise = exercise
            val fragmAction = fragm.navArgs<PublicExercisesFragmentArgs>().value.action

            //TODO get exercise image and set it here
            exerciseImage.setImageResource(R.drawable.placeholder)
            exerciseName.text = exercise.name

            //IDE is not smart enough... (it works)
            if (fragmAction == PublicExercisesFragment.Action.VIEW_EXERCISE){
                actionButton.setOnClickListener {
                    val action = PublicExercisesFragmentDirections
                        .actionPublicExercisesFragmentToViewExerciseFragment(exercise.docId!!)
                    itemView.findNavController().navigate(action)
                }
            } else if (fragmAction == PublicExercisesFragment.Action.CHOOSE_EXERCISE){
                actionButton.text = fragm.getString(R.string.choose)
                actionButton.setOnClickListener {
                    val model:CreateWorkoutViewModel by fragm.activityViewModels()
                    model.workoutUnits.add(WorkoutUnit(exercise, 1, 1))
                    //TODO add exercise in shared viewmodel
                    itemView.findNavController().popBackStack()
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.public_exercises_list_item, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(publicExercises[position].copy(), fragm)
    }

    override fun getItemCount(): Int {
        return publicExercises.size
    }
}
