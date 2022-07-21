package team.nexters.domain.moim.usecase

import team.nexters.domain.moim.repository.MoimRepository
import javax.inject.Inject

class FetchMoimListUseCase @Inject constructor(
    private val repository: MoimRepository
) {
    suspend operator fun invoke() = runCatching {
        repository.fetchMoimList()
    }
}