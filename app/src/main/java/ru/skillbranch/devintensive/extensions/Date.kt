package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.absoluteValue

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String = "HH.mm.ss dd.MM.yy"):String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units:TimeUnits = TimeUnits.SECOND):Date{
    var time = this.time

    time+=when(units){
        TimeUnits.SECOND -> value* SECOND
        TimeUnits.MINUTE -> value* MINUTE
        TimeUnits.HOUR -> value* HOUR
        TimeUnits.DAY -> value* DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date:Date = Date()): String {
    val diff = this.time - date.time
    val timeDiff:String = when(diff.absoluteValue){
        in 0..1 * SECOND -> "только что"
        in 1 * SECOND..45 * SECOND -> if (diff < 0) "несколько секунд назад" else "через несколько секунд"
        in 45 * SECOND..75 * SECOND -> if (diff < 0) "минуту назад" else "через минуту"
        in 75 * SECOND..45 * MINUTE -> (if (diff > 0) "через " else "") +
                "${diff.absoluteValue / MINUTE} " +
                TimeUnits.MINUTE.plural((diff.absoluteValue/ MINUTE).toInt()) +
                (if (diff < 0) " назад" else "")
        in 45 * MINUTE..75 * MINUTE -> if (diff < 0) "час назад" else "через час"
        in 75 * MINUTE..22 * HOUR -> (if (diff > 0) "через " else "") +
                "${diff.absoluteValue / HOUR} " +
                TimeUnits.HOUR.plural((diff.absoluteValue/ HOUR).toInt()) +
                (if (diff < 0) " назад" else "")
        in 22 * HOUR..26 * HOUR -> if (diff < 0) "день назад" else "через день"
        in 26 * HOUR..360 * DAY -> (if (diff > 0) "через " else "") +
                "${diff.absoluteValue / DAY} " +
                TimeUnits.DAY.plural((diff.absoluteValue/ DAY).toInt()) +
                (if (diff < 0) " назад" else "")
        else-> if (diff < 0) "более года назад" else "более чем через год"
    }
    return timeDiff
}

enum class TimeUnits(private val form1:String, private val form2:String, private val form5:String){
    SECOND("секунду", "секунды", "секунд"),
    MINUTE("минуту", "минуты", "минут"),
    HOUR("час", "часа", "часов"),
    DAY("день", "дня", "дней");

    fun plural(value: Int): String{
        val n = (value % 100).toInt()
        val n1 = (value % 10).toInt()

        return when {
            n in 11..19 -> form5
            n1 in 2..4 -> form2
            n1==1 -> form1
            else -> form5
        }
    }
}