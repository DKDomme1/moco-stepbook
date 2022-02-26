package com.example.stepbook.training.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.stepbook.databinding.TrainingMenuBinding
import com.example.stepbook.training.viewmodels.CreateWorkoutViewModel

class TrainingMenuFragment : Fragment() {

    private var _binding: TrainingMenuBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TrainingMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navC = view.findNavController()
        binding.viewUserWorkouts.setOnClickListener {
            val action = TrainingMenuFragmentDirections
                .actionTrainingMenuFragmentToUserWorkoutsFragment()
            navC.navigate(action)
        }
        binding.createWorkout.setOnClickListener {
            val model: CreateWorkoutViewModel by activityViewModels()
            model.workoutUnits.clear()
            val action = TrainingMenuFragmentDirections
                .actionTrainingMenuFragmentToCreateWorkoutFragment()
            navC.navigate(action)
        }
        binding.viewPublicWorkouts.setOnClickListener {
            val action =
                TrainingMenuFragmentDirections.actionTrainingMenuFragmentToPublicWorkoutsFragment()
            navC.navigate(action)
        }
        binding.viewPublicExercises.setOnClickListener {
            val action = TrainingMenuFragmentDirections
                .actionTrainingMenuFragmentToExercisesFragment(ExercisesFragment.Action.VIEW_EXERCISE)
            navC.navigate(action)
        }
        binding.viewTrackedExercises.setOnClickListener {
            val action = TrainingMenuFragmentDirections
                .actionTrainingMenuFragmentToExercisesFragment(ExercisesFragment.Action.VIEW_TRACKED_EXERCISE)
            navC.navigate(action)
        }
    }
}