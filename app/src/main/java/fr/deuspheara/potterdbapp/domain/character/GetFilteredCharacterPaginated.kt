package fr.deuspheara.potterdbapp.domain.character

import android.util.Log
import androidx.paging.PagingData
import androidx.paging.PagingSource
import fr.deuspheara.potterdbapp.data.network.model.PotterCharacter
import fr.deuspheara.potterdbapp.data.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilteredCharacterPaginated @Inject constructor(
    private val characterRepository: CharacterRepository
) {

    private companion object {
        private const val TAG = "GetFilteredCharacterPaginated"
    }
    suspend operator fun invoke(
        sort: String?,
        name: String?,
    ): Flow<PagingData<PotterCharacter>> {
        return try {
            characterRepository.getFilteredCharacterPaginated(sort, name)
        } catch (e: Exception) {
            Log.e(TAG, "Error while fetching filtered characters", e)
            throw e
        }
    }
}