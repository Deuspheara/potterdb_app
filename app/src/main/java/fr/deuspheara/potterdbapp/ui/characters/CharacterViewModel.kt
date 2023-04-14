package fr.deuspheara.potterdbapp.ui.characters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.deuspheara.potterdbapp.data.network.model.CharacterType
import fr.deuspheara.potterdbapp.domain.character.GetFilteredCharacterPaginatedUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


data class CharacterState(
    val isInProgress: Boolean,
    val currentError: Exception?,
    val successModel: Flow<PagingData<CharacterType>>?
)

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getFilteredCharacterPaginatedUseCase: GetFilteredCharacterPaginatedUseCase
): ViewModel(){

    init{
        viewModelScope.launch {
            fetchFilteredCharacterPaginated(null,null)
        }
    }


    private val _state = MutableStateFlow(
        CharacterState(
            isInProgress = true,
            currentError = null,
            successModel = null
        )
    )

    val state: StateFlow<CharacterState>
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
            CharacterState(
                isInProgress = true,
                currentError = null,
                successModel = null
            )
        )
        _state.emit(
            try {
                CharacterState(
                    isInProgress = false,
                    currentError = null,
                    successModel = getFilteredCharacterPaginatedUseCase(
                        sort,
                        name
                    )
                )
            } catch (e: Exception) {
                CharacterState(
                    isInProgress = false,
                    currentError = e,
                    successModel = null
                )
            }
        )
    }
}