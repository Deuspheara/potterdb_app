package fr.deuspheara.potterdbapp.data.datasource

import android.util.Log
import androidx.paging.PagingSource
import com.squareup.moshi.Moshi
import fr.deuspheara.potterdbapp.core.coroutine.DispatcherModule
import fr.deuspheara.potterdbapp.data.network.api.CharacterApi
import fr.deuspheara.potterdbapp.data.network.model.PotterCharacter
import fr.deuspheara.potterdbapp.data.network.model.PotterResponse
import fr.deuspheara.potterdbapp.data.paging.ApiPagingCharacter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CharacterRemoteDataSource {
    suspend fun getCharacter(id: Int): PotterCharacter
    fun createCharacterPagingSource(
        sort: String?,
        name: String?,
    ): PagingSource<Int, PotterCharacter>
}

class CharacterRemoteDataSourceImpl @Inject constructor(
    @DispatcherModule.DispatcherIO private val ioDispatcher: CoroutineDispatcher,
    private val characterApi: CharacterApi,
    private val moshi: Moshi
) : CharacterRemoteDataSource {

    private companion object {
        private const val TAG = "CharacterRemoteDataSource"
    }

    override suspend fun getCharacter(id: Int): PotterCharacter {
        return withContext(ioDispatcher) {
            try {
                val response = characterApi.getCharacter(id)
                if (response.isSuccessful) {
                    val potterResponse = response.body() as PotterResponse<PotterCharacter>
                    potterResponse.data.attributes[0]
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
    ): PagingSource<Int, PotterCharacter> {
        return ApiPagingCharacter(
            apiCall = characterApi::getCharacters,
            dispatcher = ioDispatcher,
            moshi = moshi,
            sort = sort,
            name = name
        )
    }
}