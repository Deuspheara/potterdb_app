package fr.deuspheara.potterdbapp.domain.character

import android.util.Log
import fr.deuspheara.potterdbapp.data.network.model.CharacterLightModel
import fr.deuspheara.potterdbapp.data.network.model.CharacterType
import fr.deuspheara.potterdbapp.data.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterBySlugUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
) {
    private companion object {
        private const val TAG = "GetCharacterBySlugUseCase"
    }

    suspend operator fun invoke(
        slug : String
    ): CharacterLightModel {
        return try {
            Log.d(TAG, "Get character with slug: $slug")
            characterRepository.getCharacter(slug)
        } catch (e: Exception) {
            Log.e(TAG, "Error while fetching character", e)
            throw e
        }
    }
}