package com.ma.development.a5oddam_archieve_app.domain.usecases

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class GetCurrentDateUseCase {
    fun execute(): String {
        val currentDate = Calendar.getInstance().time
        val formatter = SimpleDateFormat("d MMM", Locale("ar"))
        val formattedDate =  formatter.format(currentDate).toString()
        Log.d("formatted date",formattedDate)
        return formattedDate
    }
}