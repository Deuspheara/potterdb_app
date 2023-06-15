package fr.deuspheara.potterdbapp.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import fr.deuspheara.potterdbapp.core.coroutine.DispatcherModule
import fr.deuspheara.potterdbapp.core.model.character.CharacterFullModel
import fr.deuspheara.potterdbapp.data.datasource.CharacterRemoteDataSource
import fr.deuspheara.potterdbapp.data.datasource.character.local.CharacterLocalDataSource
import fr.deuspheara.potterdbapp.data.network.mapper.toCharacterLight
import fr.deuspheara.potterdbapp.core.model.character.CharacterLightModel
import fr.deuspheara.potterdbapp.data.database.model.CharacterEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CharacterRepository {
    suspend fun getCharacter(slug: String): CharacterLightModel
    suspend fun getFilteredCharacterPaginated(
        sort: String?,
        name: String?
    ): Flow<PagingData<CharacterLightModel>>

    suspend fun toggleFavoriteCharacter(
        slug: String,
        characterLightModel: CharacterLightModel
    ): Boolean

    suspend fun getFavoriteCharacters(): Flow<List<CharacterLightModel>>
}

class CharacterRepositoryImpl @Inject constructor(
    private val characterRemoteDataSource: CharacterRemoteDataSource,
    private val characterLocalDataSource: CharacterLocalDataSource,
    @DispatcherModule.DispatcherIO private val ioDispatcher: CoroutineDispatcher,
) : CharacterRepository {

    private companion object {
        private const val TAG = "CharacterRepository"
    }

    /**
     * Get a character with the given id
     * @param id the id of the character
     * @return the character
     */
    override suspend fun getCharacter(slug: String): CharacterLightModel {
        return withContext(ioDispatcher) {
            try {
                characterRemoteDataSource.getCharacter(slug)
                    .toCharacterLight()
            } catch (e: Exception) {
                Log.e(TAG, "Error while fetching character with id $slug", e)
                throw e
            }
        }
    }

    /**
     * Get filtered characters paginated with the given sort and name
     * @param sort the sort to apply to the characters
     * @param name the name to filter the characters
     * @return a flow of paginated characters
     */
    override suspend fun getFilteredCharacterPaginated(
        sort: String?,
        name: String?
    ): Flow<PagingData<CharacterLightModel>> {
        return withContext(ioDispatcher) {
            try {
                Pager(
                    config = PagingConfig(
                        pageSize = 20,
                        enablePlaceholders = false
                    ),
                    pagingSourceFactory = {
                        characterRemoteDataSource.createCharacterPagingSource(
                            sort,
                            name
                        )
                    }
                ).flow.toCharacterLight()
            } catch (e: Exception) {
                Log.e(
                    TAG,
                    "Error while creating character paging source with sort $sort and name $name",
                    e
                )
                throw e
            }
        }
    }

    /**
     * Toggle the favorite status of a character
     * @param slug the id of the character
     */
    override suspend fun toggleFavoriteCharacter(
        slug: String,
        characterLightModel: CharacterLightModel
    ): Boolean {
        return withContext(ioDispatcher) {
            try {
                Log.d(TAG, "Toggle favorite character with id: $slug")
                val character = characterLocalDataSource.getCharacterBySlug(slug)
                if (character != null) {
                    characterLocalDataSource.toggleFavoriteStatus(slug, !character.isFavorite)
                    Log.d("CharacterRepository", "Character with id $slug is now ${!character.isFavorite}")
                } else {
                    val characterEntity = CharacterEntity(
                        slug = characterLightModel.slug ?: "",
                        name = characterLightModel.name ?: "",
                        image = characterLightModel.image ?: "",
                        species = characterLightModel.species ?: "",
                        gender = characterLightModel.gender ?: "",
                        house = characterLightModel.house ?: "",
                        born = characterLightModel.born ?: "",
                        height = characterLightModel.height ?: "",
                        weight = characterLightModel.weight ?: "",
                        isFavorite = true,
                    );
                    characterLocalDataSource.insertCharacterIfNotExist(characterEntity)
                }
                true
            } catch (e: Exception) {
                Log.e(TAG, "Error while toggling favorite status for character with id $slug", e)
                false
            }
        }
    }

    /**
     * Get all favorite characters
     * @return a list of favorite characters [CharacterEntity]
     */
    override suspend fun getFavoriteCharacters(): Flow<List<CharacterLightModel>> {
        return withContext(ioDispatcher) {
            try {
                characterLocalDataSource.getAllCharacters().map {
                    it.map { characterEntity ->
                        CharacterEntity.toCharacterLight(characterEntity)
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error while fetching favorite characters", e)
                throw e
            }
        }
    }


}
