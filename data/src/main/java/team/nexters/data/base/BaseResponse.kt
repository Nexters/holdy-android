package team.nexters.data.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val data: T? = null
)

data class ErrorResponse(
    val statusCode: Int,
    val message: String?,
    val data: Unit? = null
)