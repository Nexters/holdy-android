package team.nexters.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import team.nexters.shared.ResultWrapper

abstract class CoroutineUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    suspend operator fun invoke(parameters: P): ResultWrapper<R> =
        withContext(coroutineDispatcher) {
            execute(parameters)
        }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(param: P): ResultWrapper<R>
}
