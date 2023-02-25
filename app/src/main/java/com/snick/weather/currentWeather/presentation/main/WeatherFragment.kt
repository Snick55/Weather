package com.snick.weather.currentWeather.presentation.main

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.snick.weather.core.appComponent
import com.snick.weather.currentWeather.presentation.CityUi
import com.snick.weather.currentWeather.presentation.CurrentWeatherUi
import com.snick.weather.databinding.WeatherFragmentBinding
import javax.inject.Inject

class WeatherFragment : Fragment(), CitiesAdapter.Listener {

    private lateinit var binding: WeatherFragmentBinding
    private lateinit var city: String
    private  var cityName: String? = null


    @Inject
    lateinit var viewModel: WeatherViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireContext().appComponent.inject(this)
        binding = WeatherFragmentBinding.inflate(inflater, container, false)
        city = arguments?.getString(CITY_KEY) ?: ""
        viewModel.fetchWeather(city)
        viewModel.getCachedCities()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CitiesAdapter(this)
        val recyclerView = binding.mainRecycler
        recyclerView.adapter = adapter

        viewModel.cities.observe(viewLifecycleOwner) {
            Log.d("TAG", "list size = ${it.size}")
            if (it.size > 1) binding.removeCityBtn.visibility = View.VISIBLE
            adapter.setUpAdapter(it)
        }
        val cityTextView = binding.cityNameTv
        val temp = binding.temp
        val weatherDescription = binding.weatherDescription
        val feelsLike = binding.feelsLike
        val humidity = binding.humidity
        val cloudiness = binding.cloudiness
        val pressure = binding.pressure
        val visibility = binding.visibility
        val speed = binding.speed


        viewModel.observeState(viewLifecycleOwner) {
            when (it) {
                is CurrentWeatherUi.Loading -> changeProgress(binding.progressBar)
                is CurrentWeatherUi.Success -> {
                    binding.contentTop.visibility = View.VISIBLE
                    binding.content.visibility = View.VISIBLE
                    binding.errorContainer.visibility = View.GONE
                    binding.iconGroup.visibility = View.VISIBLE
                    changeProgress(binding.progressBar)
                    it.render(
                        cityTextView,
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
                else -> {
                    binding.contentTop.visibility = View.GONE
                    binding.content.visibility = View.GONE
                    binding.iconGroup.visibility = View.GONE
                    binding.errorContainer.visibility = View.VISIBLE
                    changeProgress(binding.progressBar)
                    it.handleError(binding.errorMessage)
                }
            }
        }

        binding.tryAgainBtn.setOnClickListener {
            changeProgress(binding.progressBar)
            binding.errorContainer.visibility = View.GONE
            viewModel.fetchWeather(city)
        }

        binding.removeCityBtn.setOnClickListener {
            Log.d("TAG", " cityName = $cityName , city = $city")
            viewModel.removeCity(cityName?: city)
        }

    }

    private fun changeProgress(progressBar: ProgressBar) {
        if (progressBar.visibility == View.VISIBLE)
            progressBar.visibility = View.GONE
        else progressBar.visibility = View.VISIBLE
    }

    override fun handle(cityUi: CityUi) {
        when (cityUi) {
            is CityUi.City -> {
                val name = cityUi.name
                cityName = name
                viewModel.fetchWeather(name)
            }
            else -> {
                val dialogBuilder = AlertDialog.Builder(requireContext())
                DialogManager.addCity(dialogBuilder) {
                    if (it.isBlank()) return@addCity
                    viewModel.fetchWeather(it.trim())
                    viewModel.saveCity(it.trim())
                    changeProgress(binding.progressBar)
                }
            }
        }
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