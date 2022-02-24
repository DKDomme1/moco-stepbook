package com.example.stepbook.training.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.stepbook.R
import com.example.stepbook.databinding.ViewWorkoutBinding
import com.example.stepbook.common.FirestoreViewModel
import com.example.stepbook.training.adapters.ViewWorkoutAdapter

class ViewWorkoutFragment : Fragment() {

    private val model: FirestoreViewModel by activityViewModels()
    private val args: ViewWorkoutFragmentArgs by navArgs()

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
        val workout = model.getPublicWorkouts().value!![args.position]
        //TODO set workout image
        binding.workoutImage.setImageResource(R.drawable.placeholder)

        binding.workoutTitle.text = workout.title
        binding.workoutDescription.text = workout.description
        binding.workoutUnits.adapter = ViewWorkoutAdapter(workout)
    }
}