package team.nexters.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import team.nexters.shared.ResultWrapper
import timber.log.Timber

abstract class CoroutineUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(parameters: P): ResultWrapper<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute(parameters).let {
                    ResultWrapper.Success(it)
                }
            }
        } catch (e: Exception) {
            Timber.e("api error ${e.message}")
            ResultWrapper.Error(e.message ?: "")
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(param: P): R
}
