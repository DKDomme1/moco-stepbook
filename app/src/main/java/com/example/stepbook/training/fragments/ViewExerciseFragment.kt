package com.example.stepbook.training.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.stepbook.common.FirestoreUtil
import com.example.stepbook.databinding.ViewExerciseBinding
import com.example.stepbook.training.data.Exercise
import com.example.stepbook.training.data.TrackedExercise
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.Timestamp


class ViewExerciseFragment : Fragment() {
    private var _binding : ViewExerciseBinding? = null
    private val binding get() = _binding!!
    private val args:ViewExerciseFragmentArgs by navArgs()

    private var exercise = Exercise()
    private var trackedExercise:TrackedExercise? = null

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
        setupViews()
    }

    private fun setupViews(){
        var trackedExercise: TrackedExercise?
        FirestoreUtil.getExerciseById(args.exerciseId).continueWithTask {
            exercise = it.getResult().toObject(Exercise::class.java)!!
            FirestoreUtil.getTrackedExercise(exercise)
        }.addOnCompleteListener {
            if(it.isSuccessful){
                val docs = it.result.documents
                trackedExercise =
                    if (docs.size > 0) docs[0].toObject(TrackedExercise::class.java) else null
                binding.exerciseTitle.text = exercise.name
                binding.exerciseDescription.text = exercise.description
                renderChart(binding.exerciseGraph,trackedExercise)
                binding.noteExercise.setOnClickListener {
                    EnterExerciseDataDialogFragment { value ->
                        FirestoreUtil.addDataPointToExercise(value, exercise, trackedExercise)
                            .addOnCompleteListener{
                            //make the OS reload this fragment
                            parentFragmentManager.beginTransaction().detach(this).attach(this).commit();
                            setupViews()
                        }
                    }.show(
                        childFragmentManager,
                        EnterExerciseDataDialogFragment.TAG
                    )
                }

                binding.exerciseTitle.visibility = View.VISIBLE
            } else {
                Toast.makeText(context,it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun renderChart(chart: LineChart, trackedExercise: TrackedExercise?){
        if (trackedExercise == null) return
        val data = ArrayList<Entry>()
        trackedExercise.datapoints?.keys?.forEach {
            data.add(Entry(it.toFloat(),trackedExercise.datapoints?.get(it)!!.toFloat()))
        }
        data.sortBy { it.x }
        //data.map { Entry(Timestamp(it.x.toLong(),0), it.y) }
        val lineDataSet = LineDataSet(data, "Data")
        lineDataSet.setDrawCircles(true)
        lineDataSet.enableDashedLine(10f, 0f,0f)
        lineDataSet.enableDashedHighlightLine(10f, 0f,0f)
        val lineData = LineData(lineDataSet)
        chart.setTouchEnabled(true)
        chart.setPinchZoom(true)
        chart.description.isEnabled = false
        chart.setBackgroundColor(Color.WHITE)
        chart.setGridBackgroundColor(Color.LTGRAY)
        chart.setDrawBorders(false)
        chart.setData(lineData)
        chart.invalidate()
    }
}