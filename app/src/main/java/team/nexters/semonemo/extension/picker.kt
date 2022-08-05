package team.nexters.semonemo.extension

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

internal fun Context.showDatePicker(
    setDate: (String) -> Unit = {}
) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = calendar.time
    val simpleDateFormat = SimpleDateFormat("EEEE", Locale.getDefault())
    val dayName: String = simpleDateFormat.format(date)

    DatePickerDialog(
        this,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            setDate("${mYear}년 ${mMonth + 1}월 ${mDayOfMonth}일 $dayName")
        },
        year, month, day
    ).show()
}

internal fun Context.showTimePicker(
    setTime: (String) -> Unit
) {
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)
    TimePickerDialog(
        this,
        { _, mHour, mMinute ->
            val minuteZero = if (mMinute < 10) "0" else ""
            if (mHour > 12) {
                val hourZero = if (mHour - 12 < 10) "0" else ""
                setTime("오후 $hourZero${mHour - 12} : $minuteZero$mMinute")
            } else {
                val hourZero = if (mHour < 10) "0" else ""
                setTime("오전 $hourZero$mHour : $minuteZero$mMinute")
            }
        },
        hour,
        minute,
        false
    ).show()
}