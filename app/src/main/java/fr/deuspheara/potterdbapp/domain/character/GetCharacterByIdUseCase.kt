package fr.deuspheara.potterdbapp.domain.character

import android.util.Log
import fr.deuspheara.potterdbapp.data.network.model.PotterCharacter
import fr.deuspheara.potterdbapp.data.repository.CharacterRepository
import fr.deuspheara.potterdbapp.utils.NetworkHelper
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val networkHelper: NetworkHelper
) {
    private companion object {
        private const val TAG = "GetCharacterByIdUseCase"
    }

    suspend operator fun invoke(
        id : Int
    ): PotterCharacter {
        return try {
            Log.d(TAG, "Get character with id: $id")
            characterRepository.getCharacter(id)
        } catch (e: Exception) {
            Log.e(TAG, "Error while fetching character", e)
            throw e
        }
    }

}