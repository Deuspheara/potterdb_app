package fr.deuspheara.potterdbapp.domain.character

import android.util.Log
import fr.deuspheara.potterdbapp.core.model.character.CharacterLightModel
import fr.deuspheara.potterdbapp.data.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharacterBySlugUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
) {
    private companion object {
        private const val TAG = "GetCharacterBySlugUseCase"
    }

    suspend operator fun invoke(
        slug : String
    ): Flow<CharacterLightModel> = flow {
        try {
            Log.d(TAG, "Get character with slug: $slug")
            emit(characterRepository.getCharacter(slug))
        } catch (e: Exception) {
            Log.e(TAG, "Error while fetching character", e)
            throw e
        }
    }
}