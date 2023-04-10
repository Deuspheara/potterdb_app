package fr.deuspheara.potterdbapp.data.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.deuspheara.potterdbapp.core.coroutine.DispatcherModule
import fr.deuspheara.potterdbapp.data.datasource.CharacterRemoteDataSource
import fr.deuspheara.potterdbapp.data.repository.CharacterRepository
import fr.deuspheara.potterdbapp.data.repository.CharacterRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideCharacterRepository(
        @DispatcherModule.DispatcherIO ioDispatcher: CoroutineDispatcher,
        characterRemoteDataSource: CharacterRemoteDataSource
    ): CharacterRepository {
        return CharacterRepositoryImpl(
            characterRemoteDataSource = characterRemoteDataSource,
            ioDispatcher = ioDispatcher
        )
    }
}