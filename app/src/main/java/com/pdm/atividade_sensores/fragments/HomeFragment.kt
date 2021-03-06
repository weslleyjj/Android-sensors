package com.pdm.atividade_sensores.fragments

import android.hardware.Sensor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.pdm.atividade_sensores.R
import com.pdm.atividade_sensores.databinding.HomeFragmentBinding

class HomeFragment  : Fragment(){

    lateinit var binding : HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)

        binding.acelerometro.setOnClickListener {
            Navigation.findNavController(it).navigate(HomeFragmentDirections.actionHomeFragmentToSensorFragment(Sensor.TYPE_ACCELEROMETER))
        }

        binding.proximidade.setOnClickListener {
            Navigation.findNavController(it).navigate(HomeFragmentDirections.actionHomeFragmentToSensorFragment(Sensor.TYPE_PROXIMITY))
        }

        binding.giroscopio.setOnClickListener {
            Navigation.findNavController(it).navigate(HomeFragmentDirections.actionHomeFragmentToSensorFragment(Sensor.TYPE_GYROSCOPE))
        }

        binding.luz.setOnClickListener {
            Navigation.findNavController(it).navigate(HomeFragmentDirections.actionHomeFragmentToSensorFragment(Sensor.TYPE_LIGHT))
        }

        return binding.root
    }

}