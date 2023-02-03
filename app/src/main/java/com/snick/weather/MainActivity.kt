package com.snick.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.snick.weather.currentWeather.presentation.chooseCity.ChooseCityFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = ChooseCityFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                .commit()
        }

    }
}