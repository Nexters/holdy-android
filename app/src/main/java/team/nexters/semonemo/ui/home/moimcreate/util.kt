package team.nexters.semonemo.ui.home.moimcreate

fun parseDate(date: String, time: String): String {
    val dateTokens = date.split(" ")
    var year = dateTokens[0].replace("년", "")
    var month = dateTokens[1].replace("월", "")
    if (month.length == 1) month = "0$month"
    var day = dateTokens[2].replace("일", "")
    if (day.length == 1) day = "0$day"

    val timeTokens = time.split(" ")
    val hour = if (timeTokens[0] == "오후") {
        (timeTokens[1].toInt() + 12).toString()
    } else {
        timeTokens[1]
    }
    val minute = timeTokens[3]
    return year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + "00"
}

fun isMoimCreateAvailable(
    date: String,
    startTime: String,
    endTime: String,
    address: String,
    detailAddress: String
) =
    date.isNotEmpty() && startTime.isNotEmpty() && endTime.isNotEmpty() && address.isNotEmpty() && detailAddress.isNotEmpty()