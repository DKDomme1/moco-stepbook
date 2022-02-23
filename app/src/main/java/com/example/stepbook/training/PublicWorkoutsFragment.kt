package com.example.stepbook.training

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stepbook.R
import com.example.stepbook.databinding.PublicWorkoutsBinding

class PublicWorkoutsFragment : Fragment() {

    private var _binding : PublicWorkoutsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PublicWorkoutsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val view = binding.root
        val model:PublicWorkoutsViewModel by viewModels()

        val livedata = model.getLiveData()
        val adapter = PublicWorkoutsAdapter(livedata)
        binding.publicWorkoutsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.publicWorkoutsRecyclerView.adapter = adapter
        binding.publicWorkoutsRecyclerView.setHasFixedSize(true)
        livedata.observe(this, Observer<List<WorkoutPlan>> {
            //TODO yeah i know its not efficent but time is running short...
            adapter.notifyDataSetChanged()
        })
    }
}