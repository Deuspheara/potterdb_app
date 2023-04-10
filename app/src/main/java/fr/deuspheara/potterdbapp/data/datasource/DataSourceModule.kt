package fr.deuspheara.potterdbapp.data.datasource

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.deuspheara.potterdbapp.core.coroutine.DispatcherModule
import fr.deuspheara.potterdbapp.data.network.api.CharacterApi
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideCharacterRemoteDataSource(
        @DispatcherModule.DispatcherIO ioDispatcher: CoroutineDispatcher,
        characterApi: CharacterApi,
        moshi: Moshi
    ): CharacterRemoteDataSource {
        return CharacterRemoteDataSourceImpl(
            ioDispatcher = ioDispatcher,
            characterApi = characterApi,
            moshi = moshi

        )
    }

}