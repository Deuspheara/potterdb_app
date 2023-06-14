package fr.deuspheara.potterdbapp.domain.character

import android.util.Log
import fr.deuspheara.potterdbapp.core.model.character.CharacterLightModel
import fr.deuspheara.potterdbapp.data.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ToggleFavoriteCharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    private companion object{
        private const val TAG = "ToggleFavoriteCharacterUseCase"
    }

    suspend operator fun invoke(
        slug : String,
        characterLightModel: CharacterLightModel
    ) : Flow<Boolean> = flow {
        try {
            Log.d(TAG, "Toggle favorite character with slug: $slug")
            emit(characterRepository.toggleFavoriteCharacter(slug, characterLightModel))
        } catch (e: Exception) {
            Log.e(TAG, "Error while fetching character", e)
            throw e
        }
    }
}