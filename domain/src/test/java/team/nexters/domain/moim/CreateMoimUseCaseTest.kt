package team.nexters.domain.moim

import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import team.nexters.domain.moim.model.MoimResponseModel
import team.nexters.domain.moim.usecase.CreateMoimUseCase
import team.nexters.shared.ResultWrapper

@ExtendWith(MockKExtension::class)
class CreateMoimUseCaseTest {

    @MockK
    private lateinit var createMoimUseCase: CreateMoimUseCase

    private val moimCreateModel = CreateMoimUseCase.Param(
        "2022-07-29T15:00:00",
        "2022-07-29T18:00:00",
        CreateMoimUseCase.Place(
            "상록이집",
            "상록이집주소",
            "안알랴줌"
        )
    )

    @BeforeEach
    fun init() {
        coEvery {
            createMoimUseCase(
                moimCreateModel
            )
        } returns ResultWrapper.Success(MoimResponseModel(101))
    }

    @DisplayName("test success")
    @Test
    fun `Usecase return MoimResponseModel`() = runTest {
        val result = createMoimUseCase(moimCreateModel)
        assert(result is ResultWrapper.Success)
    }
}