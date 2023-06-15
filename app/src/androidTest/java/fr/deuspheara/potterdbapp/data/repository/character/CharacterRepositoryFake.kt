package fr.deuspheara.potterdbapp.data.repository.character

import androidx.paging.*
import fr.deuspheara.potterdbapp.TestingModelProvider
import fr.deuspheara.potterdbapp.data.network.mapper.toCharacterLight
import fr.deuspheara.potterdbapp.core.model.character.CharacterLightModel
import fr.deuspheara.potterdbapp.data.paging.CharacterPagingSourceFake
import fr.deuspheara.potterdbapp.data.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class CharacterRepositoryFake @Inject constructor() : CharacterRepository {
    override suspend fun getCharacter(slug: String): CharacterLightModel {
        return TestingModelProvider.providePotterCharacter().toCharacterLight()
    }

    override suspend fun getFilteredCharacterPaginated(
        sort: String?,
        name: String?
    ): Flow<PagingData<CharacterLightModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharacterPagingSourceFake() }
        ).flow.toCharacterLight()
    }

    override suspend fun toggleFavoriteCharacter(
        slug: String,
        characterLightModel: CharacterLightModel
    ): Boolean {
        return true
    }

    override suspend fun getFavoriteCharacters(): Flow<List<CharacterLightModel>> {
        return flowOf(
            listOf(
                TestingModelProvider.provideCharacterTypeWithId("").toCharacterLight(),
                TestingModelProvider.provideCharacterTypeWithId("").toCharacterLight(),
            )
        )
    }

}
