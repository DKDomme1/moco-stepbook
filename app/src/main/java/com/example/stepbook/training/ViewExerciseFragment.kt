package com.example.stepbook.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.stepbook.R
import com.example.stepbook.databinding.ViewExerciseBinding
import com.example.stepbook.databinding.ViewWorkoutBinding

class ViewExerciseFragment(val exercise: Exercise) : Fragment() {
    private var _binding : ViewExerciseBinding? = null
    private val binding get() = _binding!!

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
        binding.exerciseTitle.text = exercise.name
        binding.exerciseDescription.text = exercise.description
        binding.noteExercise.setOnClickListener {
            TODO("Go to fragemnt to enter a value to note down")
        }
    }
}