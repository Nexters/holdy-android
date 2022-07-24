package team.nexters.data.extension

import retrofit2.Response
import team.nexters.data.datastore.DataStoreManager
import team.nexters.data.user.model.response.LoginResponse

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

internal suspend fun Response<LoginResponse>.headerIntercept(
    dataStoreManager: DataStoreManager,
): Response<LoginResponse> {
    val body = checkNotNull(body())
    if (body.loginUser != null) {
        val headers = headers()
        val cookie = checkNotNull(headers["Set-Cookie"])
        dataStoreManager.setSession(cookie)
    }
    return this
}