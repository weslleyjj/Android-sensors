package com.pdm.atividade_sensores.fragments

import android.annotation.SuppressLint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
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

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.sensor_fragment, container, false)

        args = SensorFragmentArgs.fromBundle(requireArguments())

        mAccelerometer = (activity as MainActivity).mSensorManager.getDefaultSensor(args.tipoSensor)

        resetSensores()

        binding.start.setOnClickListener {
            resetSensores()
            resumeReading(it)
        }

        binding.stop.setOnClickListener {
            resetSensores()
            pauseReading(it)
        }

        binding.voltar.setOnClickListener {
            pauseReading(it)
            Navigation.findNavController(it).navigate(SensorFragmentDirections.actionSensorFragmentToHomeFragment())
        }

        return binding.root
    }

    fun resetSensores(){
        when(args.tipoSensor){
            //Acelerometro
            1 -> {
                binding.value0.text = "Mova para esquerda ou direita"
                binding.value1.text = "Mova para cima ou para baixo"
                binding.value3.text = "Mova pra frente e pra trás"
                binding.value0.isVisible = true
                binding.value1.isVisible = true
                binding.value3.isVisible = true
            }
            //Proximidade
            8 -> {
                binding.value0.text = "Aproxime ou afaste o dispositivo"
                binding.value0.isVisible = true
            }
            //Giroscópio
            4 -> {
                binding.value0.text = "Gire para frente ou para trás"
                binding.value1.text = "Gire para esquerda ou direita"
                binding.value3.text = "Gire no sentido horário"
                binding.value0.isVisible = true
                binding.value1.isVisible = true
                binding.value3.isVisible = true
            }
            //Luminosidade
            5 -> {
                binding.value0.text = "Altere a luminosidade do local"
                binding.value0.isVisible = true
            }
        }

        binding.value0.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        binding.value1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
        binding.value3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null && resume) {
            when (event.sensor.type) {
                1 -> {
                    if(event.values[0] > 1f || event.values[0] < -1f){
                        binding.value0.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0,0,0)
                    }
                    if((event.values[1] > 1f || event.values[1] < -1f) && event.values[1] != 9.809989f){
                        binding.value1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0,0,0)
                    }
                    if(event.values[2] > 1f || event.values[2] < -1f){
                        binding.value3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0,0,0)
                    }
                }
                8 -> {
                    if(event.values[0] > 1f || event.values[0] < -1f){
                        binding.value0.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0,0,0)
                    }
                }
                4 -> {
                    if(event.values[1] > 1f || event.values[1] < -1f){
                        binding.value1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0,0,0)
                    }
                    if(event.values[2] > 1f || event.values[2] < -1f){
                        binding.value3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0,0,0)
                    }
                    if(event.values[0] > 1f || event.values[0] < -1f){
                        binding.value0.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0,0,0)
                    }
                }
                5 -> {
                    if(event.values[0] > 1f || event.values[0] < -1f){
                        binding.value0.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0,0,0)
                    }
                }

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