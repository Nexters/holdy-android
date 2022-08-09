package team.nexters.domain.user

import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import team.nexters.domain.user.model.LoginModel
import team.nexters.domain.user.usecase.LoginUseCase
import team.nexters.shared.ResultWrapper

@ExtendWith(MockKExtension::class)
class LoginUseCaseTest {

    @MockK
    private lateinit var loginUseCase: LoginUseCase

    @BeforeEach
    fun init() {
        coEvery {
            loginUseCase(LoginUseCase.Param("success_key"))
        } returns ResultWrapper.Success(LoginModel(10, "evergreen", "맥주값"))
    }

    @DisplayName("Success Test")
    @Test
    fun `Usecase return LoginModel`() = runTest {
        val result = loginUseCase(LoginUseCase.Param("success_key"))
        assert(result is ResultWrapper.Success)
    }

    @DisplayName("Fail Test")
    @Test
    fun `Usecase return null user`() = runTest {
        val result = loginUseCase(LoginUseCase.Param("failed_key"))
        assert(result is ResultWrapper.Error)
    }
}