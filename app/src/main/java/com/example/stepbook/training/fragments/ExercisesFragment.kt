package com.example.stepbook.training.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.stepbook.common.FirestoreUtil
import com.example.stepbook.databinding.ExercisesBinding
import com.example.stepbook.training.adapters.ExercisesAdapter
import com.example.stepbook.training.data.Exercise
import com.example.stepbook.training.data.TrackedExercise

class ExercisesFragment : Fragment() {
    enum class Action {
        CHOOSE_EXERCISE,
        VIEW_EXERCISE,
        VIEW_TRACKED_EXERCISE
    }

    private var _binding: ExercisesBinding? = null
    private val binding get() = _binding!!

    private val args: ExercisesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ExercisesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (args.action) {
            //IDE is not smart enough... (it works)
            ExercisesFragment.Action.VIEW_TRACKED_EXERCISE -> {
                val publicExercises = ArrayList<Exercise>()
                val trackedExercises = ArrayList<Exercise>()
                FirestoreUtil.fetchPublicExercises().continueWithTask { it ->

                    it.result.documents.forEach { document ->
                        publicExercises.add(document.toObject(Exercise::class.java)!!)
                    }
                    FirestoreUtil.getTrackedExercises()
                }.addOnCompleteListener {
                    if (it.isSuccessful) {
                        it.result.documents.forEach { document ->
                            val ex = document.toObject(TrackedExercise::class.java)
                            if (ex != null) {
                                publicExercises.forEach { publEx ->
                                    if (publEx.docId == ex.exerciseDocId) trackedExercises.add(
                                        publEx
                                    )
                                }
                            }
                        }
                        if (trackedExercises.isNullOrEmpty()) binding.emptyNotice.visibility =
                            View.VISIBLE
                        binding.exercises.adapter = ExercisesAdapter(trackedExercises, this)
                        binding.exercises.setHasFixedSize(true)
                    } else {
                        //TODO show some error..
                    }
                }
            }
            else -> {
                FirestoreUtil.fetchPublicExercises()
                    .addOnSuccessListener {
                        val exercises = ArrayList<Exercise>()
                        for (exercise in it.documents) {
                            exercise.toObject(Exercise::class.java)
                                ?.let { it1 -> exercises.add(it1) }
                        }
                        binding.exercises.adapter = ExercisesAdapter(exercises, this)
                        binding.exercises.setHasFixedSize(true)
                    }
                    .addOnFailureListener {
                        //TODO show some error..
                    }
            }
        }
    }
}
