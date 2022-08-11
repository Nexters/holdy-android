package team.nexters.shared

sealed class ResultWrapper<out T> {
    class Success<out T>(val value: T) : ResultWrapper<T>()
    class Error(val message: String) : ResultWrapper<Nothing>()
    class Exception(val e: Throwable) : ResultWrapper<Nothing>()
}

inline fun <T, R> ResultWrapper<T>.flatMap(transform: (T) -> R) =
    when (this) {
        is ResultWrapper.Success -> ResultWrapper.Success(transform(value))
        is ResultWrapper.Error -> ResultWrapper.Error(this.message)
        is ResultWrapper.Exception -> ResultWrapper.Exception(this.e)
    }