package fr.deuspheara.potterdbapp.domain.character

import android.util.Log
import androidx.paging.PagingData
import fr.deuspheara.potterdbapp.core.model.character.CharacterLightModel
import fr.deuspheara.potterdbapp.data.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFilteredCharacterPaginatedUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {

    private companion object {
        private const val TAG = "GetFilteredCharacterPaginated"
    }

    suspend operator fun invoke(
        sort: String?,
        name: String?,
    ): Flow<PagingData<CharacterLightModel>> {
       return try {
           Log.d(TAG, "Get filtered characters paginated with $sort and $name")
           characterRepository.getFilteredCharacterPaginated(sort, name)
       } catch (e: Exception) {
           Log.e(TAG, "Error while fetching filtered characters", e)
           throw e
       }
    }
}