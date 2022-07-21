package team.nexters.data

import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import team.nexters.data.moim.mapper.toDomain
import team.nexters.domain.moim.repository.MoimRepository
import team.nexters.util.jsonStringToModel

@ExtendWith(MockKExtension::class)
class MoimRepositoryTest {

    @MockK
    private lateinit var moimRepository: MoimRepository

    @BeforeEach
    fun init() {
        coEvery {
            moimRepository.fetchMoimList()
        } returns MoimRepositoryDummy
            .moims
            .jsonStringToModel().map {
                it.toDomain()
            }

    }

    @DisplayName("moim repository success")
    @Test
    fun `fetch moim response first id is 36`() = runTest {
        val users = moimRepository.fetchMoimList()
        assert(users[0].id == 36)
    }

    @DisplayName("moim repository failed")
    @Test
    fun `fetch moim response first id is not 36`() = runTest {
        val users = moimRepository.fetchMoimList()
        assert(users[0].id == 37)
    }
}