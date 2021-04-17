package com.pdm.atividade_sensores.fragments

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.pdm.atividade_sensores.MainActivity
import com.pdm.atividade_sensores.R
import com.pdm.atividade_sensores.databinding.AcelerometerFragmentBinding

class AcelerometerFragment : Fragment(), SensorEventListener {

    lateinit var binding : AcelerometerFragmentBinding
    private var mAccelerometer : Sensor ?= null
    private var resume = false;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.acelerometer_fragment, container, false)

        mAccelerometer = (activity as MainActivity).mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        binding.start.setOnClickListener {
            resumeReading(it)
        }

        binding.stop.setOnClickListener {
            pauseReading(it)
        }

        binding.voltar.setOnClickListener {
            pauseReading(it)
            Navigation.findNavController(it).navigate(R.id.action_acelerometerFragment_to_homeFragment)
        }

        return binding.root
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null && resume) {
            if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                binding.textSensor.text = event.values[0].toString()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }override fun onPause() {
        super.onPause()
        (activity as MainActivity).mSensorManager.unregisterListener(this)
    }
    fun resumeReading(view: View) {
        this.resume = true
    }

    fun pauseReading(view: View) {
        this.resume = false
    }

}