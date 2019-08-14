package com.example.meteo

import android.graphics.Typeface
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun getDate(milliSeconds:Long): String? {
    val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("HH:mm", Locale.FRANCE)
    simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis.times(milliSeconds)
    return simpleDateFormat.format(calendar.time)
}

@RequiresApi(Build.VERSION_CODES.O)
fun currentDate():String{
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("d MMM")
    return current.format(formatter)
}
