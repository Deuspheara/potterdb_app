package fr.deuspheara.potterdbapp.data.datasource

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import fr.deuspheara.potterdbapp.data.datasource.character.CharacterRemoteDataSourceFake
import fr.deuspheara.potterdbapp.data.repository.CharacterRepository
import fr.deuspheara.potterdbapp.data.repository.RepositoryModule
import fr.deuspheara.potterdbapp.data.repository.character.CharacterRepositoryFake


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
}