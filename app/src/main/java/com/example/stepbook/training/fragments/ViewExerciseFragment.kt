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
import com.example.stepbook.training.adapters.TrackedExerciseAdapter
import com.example.stepbook.training.data.Exercise
import com.example.stepbook.training.data.TrackedExercise
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.Timestamp


class ViewExerciseFragment : Fragment() {
    private var _binding: ViewExerciseBinding? = null
    private val binding get() = _binding!!
    private val args: ViewExerciseFragmentArgs by navArgs()

    private var exercise = Exercise()
    private var trackedExercise: TrackedExercise? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ViewExerciseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        var trackedExercise: TrackedExercise?
        FirestoreUtil.getExerciseById(args.exerciseId).continueWithTask {
            exercise = it.result.toObject(Exercise::class.java)!!
            FirestoreUtil.getTrackedExerciseByPublicExercise(exercise)
        }.addOnCompleteListener {
            if (it.isSuccessful) {
                val docs = it.result.documents
                trackedExercise =
                    if (docs.size > 0) docs[0].toObject(TrackedExercise::class.java) else null
                binding.exerciseTitle.text = exercise.name
                binding.exerciseDescription.text = exercise.description
                binding.exerciseTitle.visibility = View.VISIBLE
                renderChart(binding.exerciseGraph, trackedExercise)
                binding.noteExercise.setOnClickListener {
                    EnterExerciseDataDialogFragment { value ->
                        if (value > 0) {
                            FirestoreUtil.addDataPointToExercise(value, exercise, trackedExercise)
                                .addOnCompleteListener {
                                    //make the OS reload this fragment
                                    parentFragmentManager.beginTransaction().detach(this)
                                        .attach(this).commit()
                                    setupViews()
                                }
                        }
                    }.show(
                        childFragmentManager,
                        EnterExerciseDataDialogFragment.TAG
                    )
                }
                binding.datapoints.adapter =
                    TrackedExerciseAdapter(trackedExercise, { setupViews() })

            } else {
                Toast.makeText(context, it.exception!!.message.toString(), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun renderChart(chart: LineChart, trackedExercise: TrackedExercise?) {
        if (trackedExercise == null) return
        val data = ArrayList<Entry>()
        trackedExercise.datapoints?.keys?.forEach {
            val relativeTime = (it.toLong() - Timestamp.now().seconds)
            data.add(
                Entry(
                    relativeTime.toFloat(),
                    trackedExercise.datapoints?.get(it)!!.toFloat()
                )
            )
        }
        var counter = 0f
        data.sortBy { it.x }
        val processedData = data.map { Entry(counter++, it.y) }
        //data.map { Entry(Timestamp(it.x.toLong(),0), it.y) }
        val lineDataSet = LineDataSet(processedData, "Data")
        lineDataSet.setDrawCircles(true)
        lineDataSet.enableDashedLine(10f, 0f, 0f)
        lineDataSet.enableDashedHighlightLine(10f, 0f, 0f)
        lineDataSet.lineWidth = 2f
        lineDataSet.circleRadius = 6f
        val lineData = LineData(lineDataSet)
        lineData.setValueTextColor(Color.BLACK)
        lineData.setValueTextSize(11f)
        chart.data = lineData
        chart.setTouchEnabled(true)
        chart.setScaleEnabled(true)
        chart.setPinchZoom(false)
        chart.isDoubleTapToZoomEnabled = false
        chart.description.isEnabled = false
        chart.setBackgroundColor(Color.WHITE)
        chart.setGridBackgroundColor(Color.LTGRAY)
        chart.setDrawBorders(false)
        chart.fitScreen()
        chart.resetViewPortOffsets()
        chart.invalidate()
    }
}