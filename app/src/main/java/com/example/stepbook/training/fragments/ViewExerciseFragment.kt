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
import com.example.stepbook.common.FirestoreUtil

class ViewExerciseFragment : Fragment() {
    private var _binding : ViewExerciseBinding? = null
    private val binding get() = _binding!!
    private val args:ViewExerciseFragmentArgs by navArgs()

    private var exercise = Exercise()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ViewExerciseBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirestoreUtil.getExerciseById(args.exerciseId).addOnSuccessListener {
            val t = it.toObject(Exercise::class.java)
            if(t != null) exercise = t
            binding.exerciseTitle.text = exercise.name
            binding.exerciseDescription.text = exercise.description
            binding.noteExercise.setOnClickListener {
                TODO("Go to fragemnt to enter a value to note down")
            }

            binding.exerciseTitle.visibility = View.VISIBLE
        }
    }
}