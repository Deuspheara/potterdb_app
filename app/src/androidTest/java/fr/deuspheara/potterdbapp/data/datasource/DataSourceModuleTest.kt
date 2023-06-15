package fr.deuspheara.potterdbapp.data.datasource

import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import fr.deuspheara.potterdbapp.data.datasource.character.local.CharacterLocalDataSource
import fr.deuspheara.potterdbapp.data.datasource.character.local.CharacterLocalDataSourceFake
import fr.deuspheara.potterdbapp.data.datasource.character.remote.CharacterRemoteDataSourceFake


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataSourceModule::class]
)
abstract class DataSourceModuleTest {

    @Binds
    abstract fun provideCharacterRemoteDataSource(
        fake : CharacterRemoteDataSourceFake
    ) : CharacterRemoteDataSource

    @Binds
    abstract fun bindsCharacterLocalDatasource(
        fake : CharacterLocalDataSourceFake
    ): CharacterLocalDataSource
}