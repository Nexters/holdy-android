package team.nexters.data.extension

import retrofit2.Response

internal fun <T> Response<T>.apiCall(): T {
    val body = body()
    return if (isSuccessful && body != null) {
        body
    } else {
        throw IllegalStateException(
            """
            Request is fail.
            Http message: ${errorBody()?.use { it.string() }}
            """.trimIndent()
        )
    }
}