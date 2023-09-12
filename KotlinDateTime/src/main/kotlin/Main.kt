
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.concurrent.schedule

fun main() {
    val localTime = LocalTime.now()
    println("Local: $localTime")
    val midnight = LocalTime.MIDNIGHT
    val duration = ChronoUnit.MILLIS.between(midnight, localTime)

    val timeFormat = DateTimeFormatter.ofPattern("HH:mm")
    val input = "16:48"
    val inputInFormat = LocalTime.parse(input, timeFormat)
    println("Input Time: $input")

    val timeToExecute = inputInFormat.minusMinutes(2)
    println("Execution Time: $timeToExecute")

    val hours = timeToExecute.hour
    val minutes = timeToExecute.minute
    val timeInMillis1 = (hours * 60 + minutes) * 60 * 1000L

    val delay = timeInMillis1 - duration
    println(delay)

    Timer().schedule(delay) {
        println("SMS... ${LocalTime.now()}")
    }
}
