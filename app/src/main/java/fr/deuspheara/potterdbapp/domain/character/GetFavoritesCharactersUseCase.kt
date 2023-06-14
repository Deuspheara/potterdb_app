package fr.deuspheara.potterdbapp.domain.character

import android.util.Log
import androidx.paging.PagingData
import fr.deuspheara.potterdbapp.core.model.character.CharacterLightModel
import fr.deuspheara.potterdbapp.data.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFavoritesCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
){
    /**
     * Get filtered characters paginated with the given sort and name
     * @param sort the sort to apply to the characters
     * @param name the name to filter the characters
     * @return a flow of a list of [CharacterLightModel]
     */
    suspend operator fun invoke(): Flow<List<CharacterLightModel>> {
        Log.d("CharacterViewModel", "Fetch characters")
        return try {
            characterRepository.getFavoriteCharacters()
        } catch (e: Exception) {
            Log.e("CharacterViewModel", "Error while fetching characters", e)
            throw e
        }
    }
}
