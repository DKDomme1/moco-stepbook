package com.example.stepbook.training.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.stepbook.databinding.TrainingMenuBinding

class TrainingMenuFragment : Fragment() {

    private var _binding : TrainingMenuBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TrainingMenuBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewUserWorkouts.setOnClickListener {
            val action = TrainingMenuFragmentDirections
                .actionTrainingMenuFragmentToUserWorkoutsFragment()
            view.findNavController().navigate(action)
        }
        binding.viewPublicWorkouts.setOnClickListener {
            val action = TrainingMenuFragmentDirections
                .actionTrainingMenuFragmentToPublicWorkoutsFragment()
            view.findNavController().navigate(action)
        }
        binding.viewPublicExercises.setOnClickListener {
            val action = TrainingMenuFragmentDirections
                .actionTrainingMenuFragmentToPublicExercisesFragment()
            view.findNavController().navigate(action)
        }
    }
}