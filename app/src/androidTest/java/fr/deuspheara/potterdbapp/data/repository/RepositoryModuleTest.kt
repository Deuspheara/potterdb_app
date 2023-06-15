package fr.deuspheara.potterdbapp.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import fr.deuspheara.potterdbapp.data.repository.CharacterRepository
import fr.deuspheara.potterdbapp.data.repository.RepositoryModule
import fr.deuspheara.potterdbapp.data.repository.character.CharacterRepositoryFake

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
abstract class RepositoryModuleTest {
    @Binds
    abstract fun provideCharacterRepository(
        fake: CharacterRepositoryFake
    ) : CharacterRepository
}