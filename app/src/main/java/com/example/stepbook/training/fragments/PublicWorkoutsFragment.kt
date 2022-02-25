package com.example.stepbook.training.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.stepbook.training.data.WorkoutPlan
import com.example.stepbook.databinding.PublicWorkoutsBinding
import com.example.stepbook.common.FirestoreUtil
import com.example.stepbook.training.adapters.PublicWorkoutsAdapter

class PublicWorkoutsFragment : Fragment() {

    private var _binding : PublicWorkoutsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PublicWorkoutsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirestoreUtil.fetchPublicWorkouts().addOnSuccessListener { publicWorkoutQuery ->
            val publicWorkouts = ArrayList<WorkoutPlan>()
            for (document in publicWorkoutQuery.documents){
                document.toObject(WorkoutPlan::class.java)?.let { publicWorkouts.add(it) }
            }
            FirestoreUtil.fetchUserWorkouts()
                .addOnSuccessListener { userWorkoutQuery->
                    val userWorkouts = ArrayList<WorkoutPlan>()
                    for (document in userWorkoutQuery.documents){
                        document.toObject(WorkoutPlan::class.java)?.let { userWorkouts.add(it) }
                    }
                    binding.publicWorkouts.adapter = PublicWorkoutsAdapter(publicWorkouts, userWorkouts)
                }
                .addOnFailureListener {
                    binding.publicWorkouts.adapter = PublicWorkoutsAdapter(publicWorkouts, null)
                    //TODO make toast
                }
        }
        .addOnFailureListener {
            //TODO make toast
        }
        binding.publicWorkouts.setHasFixedSize(true)
    }
}