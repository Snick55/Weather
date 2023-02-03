package com.snick.weather.currentWeather.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.snick.weather.core.WeatherApp
import com.snick.weather.currentWeather.presentation.WeatherUi
import com.snick.weather.databinding.WeatherFragmentBinding

class WeatherFragment: Fragment() {

private lateinit var binding: WeatherFragmentBinding
private lateinit var viewModel: WeatherViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WeatherFragmentBinding.inflate(inflater,container,false)
        val city = arguments?.getString(CITY_KEY)
        viewModel = (requireContext().applicationContext as WeatherApp).weatherViewModel
        viewModel.fetchWeather(city?:"")
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner){
            when(it){
                is WeatherUi.Loading -> binding.cityNameTv.text = "loading" // TODO: make progress
                is WeatherUi.Success -> it.render(binding.cityNameTv)
                else -> it.render(binding.cityNameTv) //it.handleError(binding.error_container)
            }
        }

    }

    companion object{
        fun newInstance(city: String):WeatherFragment {
            val args = bundleOf()
            args.putString(CITY_KEY,city)
            val fragment = WeatherFragment()
            fragment.arguments = args
            return fragment
        }
        private const val CITY_KEY = "CITY_KEY"
    }


}