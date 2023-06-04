package fr.deuspheara.potterdbapp.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import com.google.gson.Gson
import fr.deuspheara.potterdbapp.core.coroutine.DispatcherModule
import fr.deuspheara.potterdbapp.data.network.api.CharacterApi
import fr.deuspheara.potterdbapp.data.network.model.PotterResponse
import fr.deuspheara.potterdbapp.data.network.model.CharacterType
import fr.deuspheara.potterdbapp.data.paging.ApiPagingCharacter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CharacterRemoteDataSource {
    suspend fun getCharacter(slug: String): CharacterType.PotterCharacter
    fun createCharacterPagingSource(
        sort: String?,
        name: String?,
    ): PagingSource<Int, CharacterType>
}

class CharacterRemoteDataSourceImpl @Inject constructor(
    @DispatcherModule.DispatcherIO private val ioDispatcher: CoroutineDispatcher,
    private val characterApi: CharacterApi,
    private val gson: Gson
) : CharacterRemoteDataSource {

    private companion object {
        private const val TAG = "CharacterRemoteDataSource"
    }

    override suspend fun getCharacter(slug: String): CharacterType.PotterCharacter {
        return withContext(ioDispatcher) {
            try {
                val response = characterApi.getCharacter(slug)
                if (response.isSuccessful) {
                    val potterResponse = response.body() as PotterResponse<CharacterType>
                    potterResponse.data.attributes
                } else {
                    throw Exception("Error while fetching character")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error while fetching character", e)
                throw e
            }
        }
    }

    override fun createCharacterPagingSource(
        sort: String?,
        name: String?,
    ): PagingSource<Int, CharacterType> {
        return ApiPagingCharacter(
            apiCall = characterApi::getCharacters,
            dispatcher = ioDispatcher,
            gson = gson,
            sort = sort,
            name = name
        )
    }
}