package fr.deuspheara.potterdbapp.data.datasource

import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.deuspheara.potterdbapp.core.coroutine.DispatcherModule
import fr.deuspheara.potterdbapp.data.datasource.character.local.CharacterLocalDataSource
import fr.deuspheara.potterdbapp.data.datasource.character.local.CharacterLocalDataSourceImpl
import fr.deuspheara.potterdbapp.data.network.api.CharacterApi
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindCharacterRemoteDataSource(
        impl: CharacterRemoteDataSourceImpl
    ): CharacterRemoteDataSource

    @Binds
    abstract fun bindCharacterLocalDataSource(
        impl: CharacterLocalDataSourceImpl
    ): CharacterLocalDataSource

}