package com.example.my_parking_app.tools

import java.text.SimpleDateFormat

class DateFormatter {

    fun hourFormat(date: Long): String {
        val simpleDateFormat = SimpleDateFormat("hh:mm a")
        return simpleDateFormat.format(date)
    }
    fun dayFormat(date: Long): String {
        val simpleDateFormat = SimpleDateFormat("EEEE d")
        return simpleDateFormat.format(date)
    }
    fun monthYearFormat(date: Long): String {
        val simpleDateFormat = SimpleDateFormat("MMMM yyyy")
        return simpleDateFormat.format(date)
    }

}