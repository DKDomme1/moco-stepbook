package com.example.stepbook.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.stepbook.R
import com.example.stepbook.databinding.PublicWorkoutsBinding
import com.example.stepbook.databinding.ViewWorkoutBinding

class ViewWorkoutFragment(val workout:WorkoutPlan) : Fragment() {

    private var _binding : ViewWorkoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ViewWorkoutBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO set workout image
        binding.workoutImage.setImageResource(R.drawable.placeholder)

        binding.workoutTitle.text = workout.title
        binding.workoutDescription.text = workout.description
        binding.workoutUnits.adapter = ViewWorkoutAdapter(workout.workout_units)
    }
}