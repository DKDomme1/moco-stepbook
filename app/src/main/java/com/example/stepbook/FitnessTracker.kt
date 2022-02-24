package com.example.stepbook

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import com.example.stepbook.databinding.FragmentFitnessTrackerBinding


/**
 * A simple [Fragment] subclass.
 */
class FitnessTracker : Fragment(R.layout.fragment_fitness_tracker),SensorEventListener {
    private lateinit var binding: FragmentFitnessTrackerBinding
    private var sensorManager:SensorManager? =null
    private var running=false
    private var totalSteps=0f
    private var previousTotalSteps=0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    this.sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager

    }

    override fun onResume() {
        super.onResume()
        running=true
        val stepSensor=sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if(stepSensor==null) {
            Toast.makeText(getActivity(),"No step sensor detected",Toast.LENGTH_SHORT).show()
        }else{
            sensorManager?.registerListener(this,stepSensor,SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (running){
            totalSteps=event!!.values[0]
            val CurrentSteps =totalSteps.toInt()-previousTotalSteps.toInt()
            binding.TakenSteps.text= CurrentSteps.toString()

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFitnessTrackerBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }


}

