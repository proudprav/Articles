package com.example.articles.utils

import java.text.DecimalFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.ln

class Utility {

    companion object
    {
        fun numberFormatter(count: Long): String? {
            if (count < 1000) return "" + count
            val exp =
                (ln(count.toDouble()) / ln(1000.0)).toInt()
            val format = DecimalFormat("0.#")
            val value: String =
                format.format(count / Math.pow(1000.0, exp.toDouble()))
            return String.format("%s%c", value, "kMBTPE"[exp - 1])
        }


       fun  timeFromNow(date : Date) : String{
           val duration = System.currentTimeMillis() - date.time

           val diffInMinutes: Long = TimeUnit.MILLISECONDS.toMinutes(duration)
           val diffInHours: Long = TimeUnit.MILLISECONDS.toHours(duration)
           val diffInDays: Long = TimeUnit.MILLISECONDS.toDays(duration)

           return when {
               diffInDays > 1 -> {
                   "${diffInDays.toString()} days"
               }
               diffInHours > 1 -> {
                   "$diffInHours hr"
               }
               else -> {
                   "$diffInMinutes min"
               }
           }
       }
    }
}