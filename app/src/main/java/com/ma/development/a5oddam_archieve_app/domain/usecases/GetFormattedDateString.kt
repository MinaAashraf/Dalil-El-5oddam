package com.ma.development.a5oddam_archieve_app.domain.usecases

import java.text.SimpleDateFormat
import java.util.*

class GetFormattedDateString  {
    fun execute(myDateString: String, yearExist: Boolean = true): String {
        if (myDateString.isEmpty())
            return myDateString
        val parser = SimpleDateFormat(if (yearExist) "d/M/yyyy" else "d/M", Locale("ar"))
        val formatter = SimpleDateFormat("d MMM", Locale("ar"))
        val date: Date? = parser.parse(myDateString)
        return formatter.format(date!!).toString()
    }
}