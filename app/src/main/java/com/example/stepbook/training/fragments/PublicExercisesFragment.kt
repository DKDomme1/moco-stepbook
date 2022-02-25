package com.example.stepbook.training.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.stepbook.common.FirestoreViewModel
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
        val model: FirestoreViewModel by activityViewModels()

        model.fetchPublicExercises()
        val livedata = model.getPublicExercises()
        val adapter = PublicExercisesAdapter(livedata)
        binding.publicExercises.adapter = adapter
        binding.publicExercises.setHasFixedSize(true)
        livedata.observe(this, {
            adapter.notifyDataSetChanged()
        })
    }
}
