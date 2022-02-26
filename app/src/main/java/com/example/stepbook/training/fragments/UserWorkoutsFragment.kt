package com.example.stepbook.training.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.stepbook.common.FirestoreUtil
import com.example.stepbook.databinding.UserWorkoutsBinding
import com.example.stepbook.training.adapters.UserWorkoutsAdapter
import com.example.stepbook.training.data.WorkoutPlan

class UserWorkoutsFragment : Fragment() {

    private var _binding: UserWorkoutsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = UserWorkoutsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirestoreUtil.fetchUserWorkouts().addOnSuccessListener { userWorkoutQuery ->
            val userWorkouts = ArrayList<WorkoutPlan>()
            for (document in userWorkoutQuery.documents) {
                document.toObject(WorkoutPlan::class.java)?.let { userWorkouts.add(it) }
            }
            binding.userWorkouts.adapter = UserWorkoutsAdapter(userWorkouts)
        }.addOnFailureListener {
                //TODO make toast
        }
        binding.userWorkouts.setHasFixedSize(true)
    }
}
