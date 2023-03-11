package com.snick.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.snick.weather.core.appComponent
import com.snick.weather.currentWeather.presentation.MainViewModel
import com.snick.weather.currentWeather.presentation.chooseCity.ChooseCityFragment
import com.snick.weather.currentWeather.presentation.main.WeatherFragment
import com.snick.weather.currentWeather.presentation.main.WeatherViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            viewModel.start {
               val fragment = if (it.isBlank())
                    ChooseCityFragment()
                else
                    WeatherFragment()

                supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()
            }
        }

    }
}