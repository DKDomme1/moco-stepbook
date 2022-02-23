package com.example.stepbook.training

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.stepbook.R

class PublicWorkoutsAdapter(data: LiveData<List<WorkoutPlan>>)
    : RecyclerView.Adapter<PublicWorkoutsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var workoutPlan:WorkoutPlan? = null

        val workoutName:TextView
        val workoutImage:ImageView
        val viewWorkoutButton:Button
        val addWorkoutToList:Button
        init {
            workoutName = itemView.findViewById(R.id.workout_name)
            workoutImage = itemView.findViewById(R.id.workout_image)
            viewWorkoutButton = itemView.findViewById(R.id.view_workout)
            addWorkoutToList = itemView.findViewById(R.id.add_workout_to_list)
        }
        fun setData(workoutPlan: WorkoutPlan){
            this.workoutPlan = workoutPlan

            workoutImage.setImageResource(R.drawable.placeholder)
            workoutName.setText(workoutPlan.title)

            viewWorkoutButton.setOnClickListener {
                //TODO go to next fragment
            }
            addWorkoutToList.setOnClickListener {
                //TODO add to user list
            }
        }

    }

    val data: LiveData<List<WorkoutPlan>> = data

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