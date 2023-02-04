package com.snick.weather.currentWeather.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.snick.weather.core.WeatherApp
import com.snick.weather.currentWeather.presentation.CurrentWeatherUi
import com.snick.weather.databinding.WeatherFragmentBinding

class WeatherFragment : Fragment() {

    private lateinit var binding: WeatherFragmentBinding
    private lateinit var viewModel: WeatherViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WeatherFragmentBinding.inflate(inflater, container, false)
        val city = arguments?.getString(CITY_KEY)
        viewModel = (requireContext().applicationContext as WeatherApp).weatherViewModel
        viewModel.fetchWeather(city ?: "")
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val city = binding.cityNameTv
        val temp = binding.temp
        val weatherDescription = binding.weatherDescription
        val feelsLike = binding.feelsLike
        val humidity = binding.humidity
        val cloudiness = binding.cloudiness
        val pressure = binding.pressure
        val visibility = binding.visibility
        val speed = binding.speed


        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is CurrentWeatherUi.Loading -> changeProgress(binding.progressBar)
                is CurrentWeatherUi.Success -> {
                    changeProgress(binding.progressBar)
                    it.render(
                        city,
                        weatherDescription,
                        temp,
                        feelsLike,
                        humidity,
                        cloudiness,
                        pressure,
                        visibility,
                        speed
                    )

                }
                else -> it.handleError(binding.errorMessage)
            }
        }

    }

    private fun changeProgress(progressBar: ProgressBar){
        if (progressBar.visibility == View.VISIBLE)
            progressBar.visibility = View.GONE
        else progressBar.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance(city: String): WeatherFragment {
            val args = bundleOf()
            args.putString(CITY_KEY, city)
            val fragment = WeatherFragment()
            fragment.arguments = args
            return fragment
        }

        private const val CITY_KEY = "CITY_KEY"
    }


}