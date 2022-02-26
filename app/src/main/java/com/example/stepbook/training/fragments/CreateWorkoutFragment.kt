package com.example.stepbook.training.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.stepbook.common.FirestoreUtil
import com.example.stepbook.databinding.CreateWorkoutBinding
import com.example.stepbook.training.adapters.CreateWorkoutAdapter
import com.example.stepbook.training.data.WorkoutPlan
import com.example.stepbook.training.viewmodels.CreateWorkoutViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CreateWorkoutFragment : Fragment() {

    private var _binding : CreateWorkoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CreateWorkoutBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val model:CreateWorkoutViewModel by activityViewModels()
        binding.workoutUnits.adapter = CreateWorkoutAdapter(model.workoutUnits)
        binding.addWorkoutUnit.setOnClickListener {
            val action = CreateWorkoutFragmentDirections
                .actionCreateWorkoutFragmentToPublicExercisesFragment(
                    PublicExercisesFragment.Action.CHOOSE_EXERCISE
                )
            view.findNavController().navigate(action)
        }
        binding.saveWorkout.setOnClickListener {
            if (isWorkoutValid()){
                binding.saveWorkout.isEnabled = false
                val workoutPlan = WorkoutPlan(
                    null,
                    null,
                    model.workoutUnits.toList(),
                    binding.workoutName.text.toString(),
                    binding.workoutDescription.text.toString(),
                    false,
                    Firebase.auth.currentUser!!.uid)
                FirestoreUtil.addUserDefinedWorkout(workoutPlan).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(context, "Workout created!", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    } else {
                        Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(
                    context,
                    "Please fill all fields with valid values",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun isWorkoutValid():Boolean{
        val model:CreateWorkoutViewModel by activityViewModels()
        val nameValid = binding.workoutName.text.toString().isNotBlank()
        val descValid = binding.workoutDescription.text.toString().isNotBlank()
        var hasValidUnits = true
        if(model.workoutUnits.size < 1) hasValidUnits = false
        model.workoutUnits.forEach{
            if(it.reps!! < 1 || it.sets!! < 1)  hasValidUnits=false
        }
        return nameValid && descValid && hasValidUnits
    }
}
