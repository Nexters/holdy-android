package team.nexters.shared

sealed class ResultWrapper<out T> {
    class Success<out T>(val value: T) : ResultWrapper<T>()
    class Error(val message: String) : ResultWrapper<Nothing>()
}
