package team.nexters.domain.moim.usecase

import team.nexters.domain.moim.model.MoimCreateModel
import team.nexters.domain.moim.repository.MoimRepository
import javax.inject.Inject

class CreateMoimUseCase @Inject constructor(
    private val repository: MoimRepository
) {
    suspend operator fun invoke(moimCreateModel: MoimCreateModel) = runCatching {
        repository.createMoim(moimCreateModel)
    }
}