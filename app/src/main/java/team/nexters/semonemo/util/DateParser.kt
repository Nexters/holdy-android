package team.nexters.semonemo.util

import timber.log.Timber


object DateParser {
    fun toTemplateArgs(startDate: String, endDate: String): String {
        val startTokens = startDate.split("T")
        val startDateTokens = startTokens[0].split("-")
        val startTimeTokens = startTokens[1].split(":")

        val endTokens = endDate.split("T")
        val endTimeTokens = endTokens[1].split(":")

        val year = startDateTokens[0]
        val month = if (startDateTokens[1].toInt() < 10) startDateTokens[1][1] else startDateTokens[1]
        val day = if (startDateTokens[2].toInt() < 10) startDateTokens[2][1] else startDateTokens[2]
        val startHour = startTimeTokens[0]
        val endHour = endTimeTokens[0]
        return "${year}년 ${month}월 ${day}일 ${startHour}시 - ${endHour}시"
    }
}