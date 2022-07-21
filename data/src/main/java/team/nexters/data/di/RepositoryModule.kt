package team.nexters.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.nexters.data.moim.repository.MoimRepositoryImpl
import team.nexters.domain.moim.repository.MoimRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMoimRepository(
        repository: MoimRepositoryImpl
    ): MoimRepository
}
