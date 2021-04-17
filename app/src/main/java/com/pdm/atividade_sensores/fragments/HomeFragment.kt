package com.pdm.atividade_sensores.fragments

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
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_acelerometerFragment)
        }

        binding.proximidade.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_proximityFragment)
        }

        binding.giroscopio.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_giroscopioFragment)
        }

        binding.luz.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_luminosidadeFragment)
        }

        return binding.root
    }

}