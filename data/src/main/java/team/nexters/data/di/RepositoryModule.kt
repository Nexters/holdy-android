package team.nexters.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.nexters.data.hold.repository.HoldRepositoryImpl
import team.nexters.data.moim.repository.MoimRepositoryImpl
import team.nexters.data.user.repository.UserRepositoryImpl
import team.nexters.domain.hold.repository.HoldRepository
import team.nexters.domain.moim.repository.MoimRepository
import team.nexters.domain.user.repository.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMoimRepository(
        repository: MoimRepositoryImpl
    ): MoimRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        repository: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindHoldRepository(
        repository: HoldRepositoryImpl
    ): HoldRepository
}
