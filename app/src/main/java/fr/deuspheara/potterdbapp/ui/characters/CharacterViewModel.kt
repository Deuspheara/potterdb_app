package fr.deuspheara.potterdbapp.ui.characters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.deuspheara.potterdbapp.data.network.model.PotterCharacter
import fr.deuspheara.potterdbapp.data.network.model.CharacterType
import fr.deuspheara.potterdbapp.domain.character.GetFilteredCharacterPaginatedUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject


data class CharacterDetailsState(
    val isInProgress: Boolean,
    val currentError: Exception?,
    val successModel: Flow<PagingData<CharacterType>>?
)

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getFilteredCharacterPaginatedUseCase: GetFilteredCharacterPaginatedUseCase
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
    suspend fun fetchFilteredCharacterPaginated(
        sort: String?,
        name: String?
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
                    successModel = getFilteredCharacterPaginatedUseCase(
                        sort, name
                    )


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