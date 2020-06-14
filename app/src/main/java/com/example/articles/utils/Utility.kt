package com.example.articles.utils

import java.text.DecimalFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.ln

class Utility {

    companion object {
        const val PAGE_LIMIT = 10
        const val LIKES = "Likes"
        const val COMMENTS = "Comments"

        fun Long.numberFormatter(): String? {
            if (this < 1000) return "" + this
            val exp =
                (ln(this.toDouble()) / ln(1000.0)).toInt()
            val format = DecimalFormat("0.#")
            val value: String =
                format.format(this / Math.pow(1000.0, exp.toDouble()))
            return String.format("%s%c", value, "kMBTPE"[exp - 1])
        }


        fun Date.timeFromNow(): String {
            val duration = System.currentTimeMillis() - this.time

            val diffInMinutes: Long = TimeUnit.MILLISECONDS.toMinutes(duration)
            val diffInHours: Long = TimeUnit.MILLISECONDS.toHours(duration)
            val diffInDays: Long = TimeUnit.MILLISECONDS.toDays(duration)

            return when {
                diffInDays > 1 -> {
                    "$diffInDays days"
                }
                diffInHours > 1 -> {
                    "$diffInHours hr"
                }
                else -> {
                    "$diffInMinutes min"
                }
            }
        }

        fun <E : Any, T : Collection<E>> T?.withNotNullNorEmpty(func: T.() -> Unit) {
            if (this != null && this.isNotEmpty()) {
                with(this) { func() }
            }
        }
    }
}