package fr.deuspheara.potterdbapp.data.datasource.character.local

import fr.deuspheara.potterdbapp.TestingModelProvider
import fr.deuspheara.potterdbapp.data.database.model.CharacterEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class CharacterLocalDataSourceFake @Inject constructor(

) : CharacterLocalDataSource {
    override suspend fun insertCharacterIfNotExist(entity: CharacterEntity): Long {
        return 0
    }

    override suspend fun insertAllCharacters(entities: List<CharacterEntity>): List<Long> {
        return listOf(
            0,1,2
        )
    }

    override fun getAllCharacters(): Flow<List<CharacterEntity>> {
        return flowOf(
            listOf(
                TestingModelProvider.provideCharacterEntity()
            )
        )
    }

    override suspend fun getCharacterBySlug(slug: String): CharacterEntity? {
        return TestingModelProvider.provideCharacterEntity()
    }

    override suspend fun toggleFavoriteStatus(slug: String, isFavorite: Boolean) {
        // Do nothing
    }
}