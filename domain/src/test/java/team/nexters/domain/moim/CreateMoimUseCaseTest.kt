package team.nexters.domain.moim

import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import team.nexters.domain.moim.model.MoimCreateModel
import team.nexters.domain.moim.model.MoimResponseModel
import team.nexters.domain.moim.usecase.CreateMoimUseCase

@ExtendWith(MockKExtension::class)
class CreateMoimUseCaseTest {

    @MockK
    private lateinit var createMoimUseCase: CreateMoimUseCase

    private val moimCreateModel = MoimCreateModel(
        "2022-07-29T15:00:00",
        "2022-07-29T18:00:00",
        MoimCreateModel.Place(
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
        } returns Result.success(MoimResponseModel("성공", 101))
    }

    @DisplayName("test success")
    @Test
    fun `Usecase return MoimResponseModel`() = runTest {
        val result = createMoimUseCase(moimCreateModel)
        assert(result.isSuccess)
    }
}