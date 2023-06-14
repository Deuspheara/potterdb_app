package fr.deuspheara.potterdbapp.ui.characters

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.deuspheara.potterdbapp.core.model.character.CharacterLightModel
import fr.deuspheara.potterdbapp.domain.character.GetCharacterBySlugUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import javax.inject.Inject



data class CharacterDetailsState(
    val isInProgress: Boolean,
    val currentError: Exception?,
    val successModel: CharacterLightModel?
)

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getCharacterBySlugUseCase: GetCharacterBySlugUseCase
): ViewModel(){

    private val _state = MutableStateFlow(
        CharacterDetailsState(
            isInProgress = true,
            currentError = null,
            successModel = null
        )
    )

    val state: StateFlow<CharacterDetailsState>
        get() = _state.asStateFlow()


    /**
     * Get filtered characters paginated with the given sort and name
     * @param sort the sort to apply to the characters
     * @param name the name to filter the characters
     * @return a flow of paginated characters
     */
    suspend fun fetchCharacterBySlug(
        slug : String
    ){
        Log.d("CharacterViewModel", "Fetch characters")
        _state.emit(
            CharacterDetailsState(
                isInProgress = true,
                currentError = null,
                successModel = null
            )
        )
        _state.emit(
            try {
                CharacterDetailsState(
                    isInProgress = false,
                    currentError = null,
                    successModel = getCharacterBySlugUseCase(
                        slug = slug
                    ).first()
                )
            } catch (e: Exception) {
                CharacterDetailsState(
                    isInProgress = false,
                    currentError = e,
                    successModel = null
                )
            }
        )
    }
}