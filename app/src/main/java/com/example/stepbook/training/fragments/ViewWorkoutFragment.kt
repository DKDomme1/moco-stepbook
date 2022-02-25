package com.example.stepbook.training.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stepbook.R
import com.example.stepbook.databinding.ViewWorkoutBinding
import com.example.stepbook.common.FirestoreUtil
import com.example.stepbook.training.adapters.ViewWorkoutAdapter
import com.example.stepbook.training.data.WorkoutPlan

class ViewWorkoutFragment : Fragment() {

    private val args: ViewWorkoutFragmentArgs by navArgs()

    private var _binding : ViewWorkoutBinding? = null
    private val binding get() = _binding!!

    private var workout = WorkoutPlan()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ViewWorkoutBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FirestoreUtil.getPublicWorkoutById(args.workoutId).addOnSuccessListener {
            val t = it.toObject(WorkoutPlan::class.java)
            if (t != null) workout = t
            //TODO set workout image
            binding.workoutImage.setImageResource(R.drawable.placeholder)

            binding.workoutTitle.text = workout.title
            binding.workoutDescription.text = workout.description

            val layoutManager = object : LinearLayoutManager(this.context){
                override fun canScrollVertically(): Boolean { return false }
            }
            binding.workoutUnits.layoutManager = layoutManager
            binding.workoutUnits.adapter = ViewWorkoutAdapter(workout)
        }
    }
}