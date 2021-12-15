package com.nesyou.daily.core.domain.utils

import android.content.Context
import android.text.format.DateFormat
import androidx.navigation.NavController
import com.nesyou.daily.R
import java.sql.Timestamp
import java.util.*

object Helpers {

    fun NavController.navigateReplacement(route: String) {
        this.popBackStack()
        this.navigate(route)
    }

    fun currentTimeStamp(): Timestamp = Timestamp(System.currentTimeMillis())

    fun String.capitalizeWord(): String =
        this.lowercase().replaceFirstChar { char -> char.titlecase() }


    fun dateFormatter(context: Context, date: Calendar): String {
        val now = Calendar.getInstance()
        return if (date.get(Calendar.YEAR) != now.get(Calendar.YEAR)) {
            DateFormat.format(
                "dd/MM/yyyy",
                date
            ) as String
        } else {
            if (date.get(Calendar.MONTH) != now.get(Calendar.MONTH)) {
                DateFormat.format(
                    "dd/MMMM",
                    date
                ) as String
            } else {
                when (now.get(Calendar.DAY_OF_MONTH) - date.get(Calendar.DAY_OF_MONTH)) {
                    -1 -> context.getString(R.string.tomorrow)
                    1 -> context.getString(R.string.yesterday)
                    0 -> context.getString(R.string.today)
                    else -> date.getDisplayName(
                        Calendar.DAY_OF_WEEK,
                        Calendar.LONG,
                        Locale.getDefault()
                    )!! + "/" + date.get(Calendar.DAY_OF_MONTH).toString().padStart(2, '0')
                }
            }
        }
    }

    fun <T> MutableList<T>.addOrRemove(item: T) {
        if (any { it == item }) {
            remove(item)
        } else {
            add(item)
        }
    }

    fun Calendar.toTimeStamp(): Timestamp = Timestamp(this.timeInMillis)


    fun NavController.refresh() {
        val r = currentBackStackEntry?.destination?.route!!
        popBackStack()
        navigate(r)
    }
}