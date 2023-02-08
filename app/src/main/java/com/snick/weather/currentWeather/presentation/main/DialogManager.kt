package com.snick.weather.currentWeather.presentation.main

import android.app.AlertDialog
import android.widget.EditText
import com.snick.weather.R

object DialogManager {

    fun changePass(builder: AlertDialog.Builder, block: (s:String) -> Unit){
        val newPassEditText = EditText(builder.context)
        builder.setView(newPassEditText)
        val dialog = builder.create()
        dialog.setTitle(R.string.enter_new_city)
        dialog.setButton(AlertDialog.BUTTON_POSITIVE,"OK"){ _, _ ->
            block.invoke(newPassEditText.text.toString())
            dialog.dismiss()
        }
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE,"Cancel"){ _, _ ->
            dialog.dismiss()
        }
        dialog.show()
    }

}