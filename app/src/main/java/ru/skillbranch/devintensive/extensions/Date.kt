package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String? {
  val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
  return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
  var time = this.time

  time += when (units) {
    TimeUnits.SECOND -> value * SECOND
    TimeUnits.MINUTE -> value * MINUTE
    TimeUnits.HOUR -> value * HOUR
    TimeUnits.DAY -> value * DAY
  }
  this.time = time
  return this
}

val padezhi: Map<Long, Array<String>> = mapOf(
  MINUTE to arrayOf("минуту", "минуты", "минут"),
  HOUR to arrayOf("час", "часа", "часов"),
  DAY to arrayOf("день", "дня", "дней")
)

fun Date.humanizeDiff(date: Date = Date()): String {
  val diff = date.time - this.time
  var humanDiff = "только что"
  if (diff < 2 * SECOND) {
    if (diff < (45 * SECOND)) humanDiff = "несколько секунд назад"
    else if (diff < 75 * SECOND) humanDiff = "минуту назад"
    else if (diff < (45 * MINUTE)) humanDiff = "${diff / MINUTE} минут назад"
    else if (diff < (75 * MINUTE)) humanDiff = "час назад"
    else if (diff < (22 * HOUR)) {
      if ((diff / HOUR) < 5) humanDiff = "${diff / HOUR} часа назад"
      else humanDiff = "${diff / HOUR} часов назад"
    } else if (diff < (26 * HOUR)) humanDiff = "день назад"
    else if (diff < (360 * DAY)) {
      if ((diff / DAY) < 5) humanDiff = "${diff / DAY} дня назад"
      else humanDiff = "${diff / DAY} дней назад"
    } else humanDiff = "более года назад"
  }
  if (diff < (-1 * SECOND)) {
    if (diff > (-45 * SECOND)) humanDiff = "через ${diff / -SECOND} секунду"

    humanDiff = "более чем через год"
    if (diff > (-360 * DAY)) {
      if ((diff / DAY) > -5) humanDiff = "через ${diff / -DAY} дня"
      else humanDiff = "через ${diff / -DAY} дней"
    } else if (diff > (-26 * HOUR)) humanDiff = "через день"
    else if (diff > (-22 * HOUR)) {
      if ((diff / HOUR) > -5) humanDiff = "через ${diff / -HOUR} часа"
      else humanDiff = "через ${diff / -HOUR} часов"
    } else if (diff > (-75 * MINUTE)) humanDiff = "через час"
    else if (diff > (-45 * MINUTE)) {
      if ((diff / MINUTE) > -5) humanDiff = "через ${diff / -MINUTE} минуты"
      else humanDiff = "через ${diff / -MINUTE} минут"
    } else if (diff > (-75 * SECOND)) humanDiff = "через минуту"

  }
  return humanDiff

}

enum class TimeUnits(val arr: Array<String>) {

  SECOND(arrayOf("секунду", "секунды", "секунд")),
  MINUTE(arrayOf("минуту", "минуты", "минут")),
  HOUR(arrayOf("час", "часа", "часов")),
  DAY(arrayOf("день", "дня", "дней"));

  open fun plural(value: Int): String {
    var s: String = ""
    if (value / 10 == 1 || (value % 10 in 5..9) || value % 10 == 0)
      s = "$value ${this.arr[2]}"
    else if (value % 10 == 1)
      s = "$value ${this.arr[0]}"
    else if (value % 10 < 5)
      s = "$value ${this.arr[1]}"
    return s
  }

}
