package com.example.stepbook

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.stepbook.databinding.FragmentFitnessTrackerBinding


/**
 * A simple [Fragment] subclass.
 */
class FitnessTracker : Fragment(),SensorEventListener {


    private lateinit var binding: FragmentFitnessTrackerBinding
    private var sensorManager:SensorManager? =null
    private var running=false
    private var totalSteps=0f
    private var previousTotalSteps=0f




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    this.sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager



        //loadData()
        //resetStepsonlongclick()

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentFitnessTrackerBinding.inflate(layoutInflater)
        return binding.root    }

    override fun onResume() {
        super.onResume()
        running=true
        val stepSensor=sensorManager?.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        if(stepSensor==null) {
            Toast.makeText(activity,"No step sensor detected",Toast.LENGTH_SHORT).show()
        }else{
            sensorManager?.registerListener(this,stepSensor,SensorManager.SENSOR_DELAY_UI)
        }
    }


    override fun onSensorChanged(event: SensorEvent?) {
        Log.d("LOG","Working")
        if (running){
            totalSteps=event!!.values[0]
            val currentSteps =totalSteps.toInt()-previousTotalSteps.toInt()
            binding.TakenSteps.text= currentSteps.toString()
            binding.circularProgressBar.apply {
                setProgressWithAnimation(currentSteps.toFloat())
            }
            Log.d("LOG2", "$currentSteps")

        }

    }
    fun resetStepsonlongclick (){
        binding.TakenSteps.setOnClickListener{
            Toast.makeText(requireContext(), "Long step to reset step count",Toast.LENGTH_SHORT).show()
        }
        binding.TakenSteps.setOnLongClickListener{
            previousTotalSteps= totalSteps
           binding.TakenSteps.text= 0.toString()
            saveData()

        true}
        }

    private fun saveData() {
        val sharedPreferences = requireContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putFloat("key1", previousTotalSteps)
        editor.apply()
    }

    private fun loadData() {
        val sharedPreferences = requireContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val savedNumber = sharedPreferences.getFloat("key1", 0f)
        Log.d("StepsFragment", "$savedNumber")
        previousTotalSteps = savedNumber

    }








    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }



}


