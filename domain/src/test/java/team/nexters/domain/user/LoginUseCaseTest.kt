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
import team.nexters.domain.user.model.LoginRequestModel
import team.nexters.domain.user.usecase.LoginUseCase

@ExtendWith(MockKExtension::class)
class LoginUseCaseTest {

    @MockK
    private lateinit var loginUseCase: LoginUseCase

    @BeforeEach
    fun init() {
        coEvery {
            loginUseCase(LoginRequestModel("success_key"))
        } returns Result.success(LoginModel("success", LoginModel.LoginUser("rok")))
    }

    @DisplayName("Success Test")
    @Test
    fun `Usecase return LoginModel`() = runTest {
        val result = loginUseCase(LoginRequestModel("success_key"))
        assert(result.isSuccess)
    }

    @DisplayName("Fail Test")
    @Test
    fun `Usecase return null user`() = runTest {
        val result = loginUseCase(LoginRequestModel("failed_key"))
        assert(result.isFailure)
    }
}