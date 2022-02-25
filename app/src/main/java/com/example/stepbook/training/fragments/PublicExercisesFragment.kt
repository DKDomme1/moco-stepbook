package com.example.stepbook.training.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.stepbook.common.FirestoreUtil
import com.example.stepbook.databinding.PublicExercisesBinding
import com.example.stepbook.training.adapters.PublicExercisesAdapter
import com.example.stepbook.training.data.Exercise

class PublicExercisesFragment : Fragment() {

    private var _binding : PublicExercisesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PublicExercisesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirestoreUtil.fetchPublicExercises()
            .addOnSuccessListener {
                val exercises = ArrayList<Exercise>()
                for (exercise in it.documents){
                    exercise.toObject(Exercise::class.java)?.let { it1 -> exercises.add(it1) }
                }
                binding.publicExercises.adapter = PublicExercisesAdapter(exercises)
                binding.publicExercises.setHasFixedSize(true)
            }
            .addOnFailureListener {
                //TODO make toast
            }
    }
}
