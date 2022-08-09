package team.nexters.domain.moim

import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import team.nexters.domain.moim.usecase.FetchMoimListUseCase
import team.nexters.shared.ResultWrapper

@ExtendWith(MockKExtension::class)
class FetchMoimListUseCaseTest {

    @MockK
    private lateinit var fetchMoimListUseCase: FetchMoimListUseCase

    @BeforeEach
    fun init() {
        coEvery {
            fetchMoimListUseCase(Unit)
        } returns ResultWrapper.Success(emptyList())
    }

    @DisplayName("test success")
    @Test
    fun `Usecase return empty list`() = runTest {
        val result = fetchMoimListUseCase(Unit)
        assert(result is ResultWrapper.Success)
    }
}
