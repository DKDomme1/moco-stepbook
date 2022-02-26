package com.example.stepbook.training.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stepbook.R
import com.example.stepbook.common.FirestoreUtil
import com.example.stepbook.training.data.TrackedExercise

class TrackedExerciseAdapter(val trackedExercise: TrackedExercise?, val clickFunc:()->Unit) :
    RecyclerView.Adapter<TrackedExerciseAdapter.ViewHolder>() {
    private val TAG = "CreateWorkoutAdapter"
    private var orderedKeys:ArrayList<Long> = ArrayList()
    init {
        if (trackedExercise != null){
            orderedKeys =
                trackedExercise.datapoints!!.keys.map { it.toLong() }.sortedBy { it }.toCollection(ArrayList())
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name:TextView = itemView.findViewById(R.id.datapoint_name)
        val value:TextView = itemView.findViewById(R.id.datapoint_value)
        val removePoint:Button = itemView.findViewById(R.id.remove_datapoint)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.tracked_exercise_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = orderedKeys[position].toString()
        holder.value.text = trackedExercise!!.datapoints!![orderedKeys[position].toString()].toString()
        holder.removePoint.setOnClickListener {
            val value = trackedExercise.datapoints!!.remove(holder.name.text.toString())
            FirestoreUtil.setTrackedExercise(trackedExercise).addOnCompleteListener {
                if (it.isSuccessful){
                    notifyItemRemoved(holder.adapterPosition)
                    clickFunc()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return orderedKeys.size
    }
}
