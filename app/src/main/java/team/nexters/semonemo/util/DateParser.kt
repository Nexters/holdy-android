package team.nexters.semonemo.util


object DateParser {
    fun toTemplateArgs(startDate: String, endDate: String): String {
        val startTokens = startDate.split("T")
        val startDateTokens = startTokens[0].split("-")
        val startTimeTokens = startTokens[1].split(":")

        val endTokens = endDate.split("T")
        val endTimeTokens = endTokens[1].split(":")

        val year = startDateTokens[0]
        val month =
            if (startDateTokens[1].toInt() < 10) startDateTokens[1][1] else startDateTokens[1]
        val day = if (startDateTokens[2].toInt() < 10) startDateTokens[2][1] else startDateTokens[2]
        val startHour = startTimeTokens[0]
        val endHour = endTimeTokens[0]
        return "${year}년 ${month}월 ${day}일 ${to12hour(startHour)} - ${to12hour(endHour)}"
    }

    fun toStartDate(startDate: String): String {
        val startTokens = startDate.split("T")
        val startDateTokens = startTokens[0].split("-")
        val startTimeTokens = startTokens[1].split(":")


        val year = startDateTokens[0]
        val month =
            if (startDateTokens[1].toInt() < 10) startDateTokens[1][1] else startDateTokens[1]
        val day = if (startDateTokens[2].toInt() < 10) startDateTokens[2][1] else startDateTokens[2]
        val startHour = startTimeTokens[0]
        return "${year}년 ${month}월 ${day}일 ${to12hour(startHour)}"
    }

    fun toYearMonthDay(endDate: String): String {
        val endTokens = endDate.split("T")
        val endDateTokens = endTokens[0].split("-")
        val year = endDateTokens[0]
        val month =
            if (endDateTokens[1].toInt() < 10) endDateTokens[1][1] else endDateTokens[1]
        val day = if (endDateTokens[2].toInt() < 10) endDateTokens[2][1] else endDateTokens[2]
        return "${year}년 ${month}월 ${day}일"
    }

    private fun to12hour(hour: String): String {
        val intHour = hour.toInt()
        return if (intHour <= 12) {
            "오전 ${intHour}시"
        } else {
            "오후 ${intHour - 12}시"
        }
    }
}