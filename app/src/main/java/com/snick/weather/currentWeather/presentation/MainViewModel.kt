package com.snick.weather.currentWeather.presentation

import androidx.lifecycle.ViewModel
import com.snick.weather.currentWeather.domain.LastCityUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor (
    private val useCase: LastCityUseCase
): ViewModel() {




    fun start( block: (String)->Unit){
        block.invoke(useCase.execute())
    }


}