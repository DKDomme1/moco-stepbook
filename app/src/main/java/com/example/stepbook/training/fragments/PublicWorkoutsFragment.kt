package com.example.stepbook.training.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.stepbook.common.FirestoreUtil
import com.example.stepbook.databinding.PublicWorkoutsBinding
import com.example.stepbook.training.adapters.PublicWorkoutsAdapter
import com.example.stepbook.training.data.WorkoutPlan

class PublicWorkoutsFragment : Fragment() {

    private var _binding: PublicWorkoutsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PublicWorkoutsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val publicWorkouts = ArrayList<WorkoutPlan>()
        val userWorkouts = ArrayList<WorkoutPlan>()

        FirestoreUtil.fetchPublicWorkouts().continueWithTask { publicWorkoutQuery ->
            publicWorkoutQuery.result.documents.forEach { doc ->
                publicWorkouts.add(doc.toObject(WorkoutPlan::class.java)!!)
            }
            FirestoreUtil.fetchUserWorkouts()
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                task.result.documents.forEach {
                    userWorkouts.add(it.toObject(WorkoutPlan::class.java)!!)
                }

                binding.publicWorkouts.adapter = PublicWorkoutsAdapter(publicWorkouts, userWorkouts)
            } else {
                if (task.exception != null){
                    Toast.makeText(view.context, task.exception!!.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.publicWorkouts.setHasFixedSize(true)
    }
}