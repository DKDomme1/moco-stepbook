package com.example.stepbook.training.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.stepbook.training.data.Exercise
import com.example.stepbook.databinding.ViewExerciseBinding
import com.example.stepbook.common.FirestoreViewModel

class ViewExerciseFragment : Fragment() {
    private var _binding : ViewExerciseBinding? = null
    private val binding get() = _binding!!
    private val args:ViewExerciseFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ViewExerciseBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model: FirestoreViewModel by activityViewModels()

        lateinit var exercise: Exercise
        if(args.workoutId != null){
            exercise = model
                .getWorkoutById(args.workoutId!!)
                ?.workout_units
                ?.get(args.position)?.exercise!!
        } else {
            exercise = model.getExercises().value!![args.position]
        }
        binding.exerciseTitle.text = exercise.name
        binding.exerciseDescription.text = exercise.description
        binding.noteExercise.setOnClickListener {
            TODO("Go to fragemnt to enter a value to note down")
        }
    }
}