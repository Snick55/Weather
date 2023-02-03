package com.snick.weather.currentWeather.presentation.chooseCity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.snick.weather.R
import com.snick.weather.currentWeather.presentation.main.WeatherFragment
import com.snick.weather.databinding.ChooseCityFragmentBinding

class ChooseCityFragment: Fragment() {

    private lateinit var binding: ChooseCityFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChooseCityFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.acceptBtn.setOnClickListener {
            val text = binding.cityTv.text.toString()
            if (text.isBlank()) return@setOnClickListener
            val fragment = WeatherFragment.newInstance(text)
            parentFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()
        }

    }


}