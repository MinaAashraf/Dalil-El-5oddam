package com.ma.development.a5oddam_archieve_app.presentation.adapters

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("phone")
fun bindPhone(textView: TextView, phone: Int?) {
    phone?.let {
        textView.text = "0$it"
        textView.visibility = View.VISIBLE
    } ?: run { textView.visibility = View.GONE }
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter("date")
fun bindDate(textView: TextView, myDateString: String) {
    if (myDateString.isEmpty()) {
        textView.visibility = View.GONE
        return
    }

    val parser = SimpleDateFormat("d/M/yyyy", Locale("ar"))
    val formatter = SimpleDateFormat("d MMM",Locale("ar"))
    val date: Date? = parser.parse(myDateString)
    textView.text = formatter.format(date!!).toString()
    textView.visibility = View.VISIBLE
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter("resamaDate")
fun bindResamaDate (textView: TextView, resamaDate: String?){
    if (resamaDate == null) {
        textView.visibility = View.GONE
        return
    }
    val parser = SimpleDateFormat("d/M/yyyy", Locale("ar"))
    val formatter = SimpleDateFormat("d MMM yyyy",Locale("ar"))
    val date: Date? = parser.parse(resamaDate)
    textView.text = formatter.format(date!!)
    textView.visibility = View.VISIBLE
}

@BindingAdapter("titleVisibility")
fun bindResamaDateTitle (textView: TextView, resamaDate: String?){
    textView.visibility = if (resamaDate==null) View.GONE else View.VISIBLE
}