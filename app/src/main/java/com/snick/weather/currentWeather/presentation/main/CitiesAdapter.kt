package com.snick.weather.currentWeather.presentation.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.snick.weather.currentWeather.presentation.CityUi

class CitiesAdapter: RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder>() {

    private var cities: List<CityUi> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setUpAdapter(list: List<CityUi>){
        cities = list
        notifyDataSetChanged()
        // TODO: make DiffUtils
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesViewHolder {
       return when(viewType){
           CITY -> {
               val inflater = LayoutInflater.from(parent.context)
               val binding = CityItemBinding.inflate(inflater, parent, false)
               CitiesViewHolder.City(binding)
           }
           else ->{
               val inflater = LayoutInflater.from(parent.context)
               val binding = AddItemBinding.inflate(inflater, parent, false)
               CitiesViewHolder.Add(binding)
           }
       }
    }

    override fun onBindViewHolder(holder: CitiesViewHolder, position: Int) {
        holder.bind(cities[position])
    }

    override fun getItemViewType(position: Int): Int {
      return  when(cities[position]){
            is CityUi.City -> CITY
            else -> ADD
        }
    }

    override fun getItemCount(): Int = cities.size

    abstract class CitiesViewHolder(view: View): RecyclerView.ViewHolder(view){

       open fun bind(ui: CityUi) = Unit

       class City(private val binding: CityItemBinding): CitiesViewHolder(binding.root){
           override fun bind(ui: CityUi) {
               ui.show(binding.somename)
           }
       }

       class Add(private val binding: AddItemBinding): CitiesViewHolder(binding.root)
   }


    private companion object{
        const val CITY = 0
        const val ADD = 1

    }
}