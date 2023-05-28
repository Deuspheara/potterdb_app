package fr.deuspheara.potterdbapp.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import fr.deuspheara.potterdbapp.core.coroutine.DispatcherModule
import fr.deuspheara.potterdbapp.data.datasource.CharacterRemoteDataSource
import fr.deuspheara.potterdbapp.data.network.mapper.toCharacterLight
import fr.deuspheara.potterdbapp.data.network.model.CharacterLightModel
import fr.deuspheara.potterdbapp.data.network.model.CharacterType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CharacterRepository {
    suspend fun getCharacter(slug: String): CharacterLightModel
    suspend fun getFilteredCharacterPaginated(
        sort: String?,
        name: String?
    ): Flow<PagingData<CharacterLightModel>>
}

class CharacterRepositoryImpl @Inject constructor(
    private val characterRemoteDataSource: CharacterRemoteDataSource,
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
                    pagingSourceFactory = { characterRemoteDataSource.createCharacterPagingSource(sort, name) }
                ).flow.toCharacterLight()
            } catch (e: Exception) {
                Log.e(TAG, "Error while creating character paging source with sort $sort and name $name", e)
                throw e
            }
        }
    }

}