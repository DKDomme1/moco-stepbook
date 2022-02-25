package com.example.stepbook.training.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stepbook.training.data.WorkoutPlan
import com.example.stepbook.databinding.PublicWorkoutsBinding
import com.example.stepbook.common.FirestoreViewModel
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
        val model: FirestoreViewModel by activityViewModels()

        model.fetchPublicWorkouts()
        val livedata = model.getPublicWorkouts()
        val adapter = PublicWorkoutsAdapter(livedata)
        binding.publicWorkouts.adapter = adapter
        binding.publicWorkouts.setHasFixedSize(true)
        livedata.observe(this, {
            adapter.notifyDataSetChanged()
        })
    }
}