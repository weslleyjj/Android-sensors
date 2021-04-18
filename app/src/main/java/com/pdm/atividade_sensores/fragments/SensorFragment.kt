package com.pdm.atividade_sensores.fragments

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
import com.pdm.atividade_sensores.databinding.SensorFragmentBinding

class SensorFragment : Fragment(), SensorEventListener {

    lateinit var binding : SensorFragmentBinding
    private var mAccelerometer : Sensor?= null
    private var resume = false;
    lateinit var args : SensorFragmentArgs

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.sensor_fragment, container, false)

        args = SensorFragmentArgs.fromBundle(requireArguments())

        mAccelerometer = (activity as MainActivity).mSensorManager.getDefaultSensor(args.tipoSensor)

        binding.start.setOnClickListener {
            resumeReading(it)
        }

        binding.stop.setOnClickListener {
            pauseReading(it)
        }

        binding.voltar.setOnClickListener {
            pauseReading(it)
            Navigation.findNavController(it).navigate(SensorFragmentDirections.actionSensorFragmentToHomeFragment())
        }

        return binding.root
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null && resume) {
            if (event.sensor.type == args.tipoSensor) {
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