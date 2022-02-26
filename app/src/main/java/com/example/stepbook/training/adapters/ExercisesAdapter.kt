package com.example.stepbook.training.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.stepbook.R
import com.example.stepbook.training.data.Exercise
import com.example.stepbook.training.data.WorkoutUnit
import com.example.stepbook.training.fragments.ExercisesFragment
import com.example.stepbook.training.fragments.ExercisesFragmentArgs
import com.example.stepbook.training.fragments.ExercisesFragmentDirections
import com.example.stepbook.training.viewmodels.CreateWorkoutViewModel

class ExercisesAdapter(val exercises: List<Exercise>, val fragm: ExercisesFragment) :
    RecyclerView.Adapter<ExercisesAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var exercise: Exercise? = null
        val exerciseName: TextView = itemView.findViewById(R.id.exercise_title)
        val exerciseImage: ImageView = itemView.findViewById(R.id.exercise_image)
        val actionButton: Button = itemView.findViewById(R.id.view_exercise)

        fun setData(exercise: Exercise, fragm: ExercisesFragment) {
            this.exercise = exercise
            val fragmAction = fragm.navArgs<ExercisesFragmentArgs>().value.action

            //TODO get exercise image and set it here
            exerciseImage.setImageResource(R.drawable.placeholder)
            exerciseName.text = exercise.name

            //IDE is not smart enough... (it works)
            if (fragmAction == ExercisesFragment.Action.VIEW_EXERCISE ||
                fragmAction == ExercisesFragment.Action.VIEW_TRACKED_EXERCISE) {
                actionButton.setOnClickListener {
                    val action = ExercisesFragmentDirections
                        .actionExercisesFragmentToViewExerciseFragment2(exercise.docId!!)
                    itemView.findNavController().navigate(action)
                }
            } else if (fragmAction == ExercisesFragment.Action.CHOOSE_EXERCISE) {
                actionButton.text = fragm.getString(R.string.choose)
                actionButton.setOnClickListener {
                    val model: CreateWorkoutViewModel by fragm.activityViewModels()
                    model.workoutUnits.add(WorkoutUnit(exercise, 1, 1))
                    //TODO add exercise in shared viewmodel
                    itemView.findNavController().popBackStack()
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.exercises_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(exercises[position].copy(), fragm)
    }

    override fun getItemCount(): Int {
        return exercises.size
    }
}
