package fr.deuspheara.potterdbapp.data.repository.character

import androidx.paging.*
import fr.deuspheara.potterdbapp.TestingModelProvider
import fr.deuspheara.potterdbapp.data.network.model.CharacterType
import fr.deuspheara.potterdbapp.data.paging.CharacterPagingSourceFake
import fr.deuspheara.potterdbapp.data.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryFake @Inject constructor() : CharacterRepository {
    override suspend fun getCharacter(slug: String): CharacterType.PotterCharacter {
        return TestingModelProvider.providePotterCharacter()
    }

    override suspend fun getFilteredCharacterPaginated(
        sort: String?,
        name: String?
    ): Flow<PagingData<CharacterType>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharacterPagingSourceFake() }
        ).flow
    }
}
