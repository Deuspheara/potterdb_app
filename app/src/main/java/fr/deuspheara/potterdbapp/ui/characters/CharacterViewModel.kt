package fr.deuspheara.potterdbapp.ui.characters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.deuspheara.potterdbapp.core.model.character.CharacterLightModel
import fr.deuspheara.potterdbapp.domain.character.GetFavoritesCharactersUseCase
import fr.deuspheara.potterdbapp.domain.character.GetFilteredCharacterPaginatedUseCase
import fr.deuspheara.potterdbapp.domain.character.ToggleFavoriteCharacterUseCase
import fr.deuspheara.potterdbapp.ui.favorite.FavoriteState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CharacterState(
    val isInProgress: Boolean,
    val currentError: Exception?,
    val successModel: Flow<PagingData<CharacterLightModel>>?
)

data class ToggleFavoriteState(
    val isInProgress: Boolean,
    val currentError: Exception?,
    val isCharacterFavorite: Boolean
)

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getFilteredCharacterPaginated: GetFilteredCharacterPaginatedUseCase,
    val toggleFavoriteCharacter: ToggleFavoriteCharacterUseCase,
    private val getFavoritesCharacters: GetFavoritesCharactersUseCase
) : ViewModel() {

    private val _characterState = MutableStateFlow(
        CharacterState(
            isInProgress = false,
            currentError = null,
            successModel = null
        )
    )
    private val _toggleFavoriteState = MutableStateFlow(
        ToggleFavoriteState(
            isInProgress = false,
            currentError = null,
            isCharacterFavorite = false
        )
    )

    private val _favoriteState = MutableStateFlow(
        FavoriteState(
            isInProgress = false,
            currentError = null,
            successModel = null
        )
    )

    val characterState: StateFlow<CharacterState> = _characterState.asStateFlow()
    val toggleFavoriteState: StateFlow<ToggleFavoriteState> = _toggleFavoriteState.asStateFlow()
    val favoriteState: StateFlow<FavoriteState> = _favoriteState.asStateFlow()

    init {
        fetchFilteredCharacterPaginated(null, null)
        fetchFavoritesCharacters()
    }

    fun fetchFilteredCharacterPaginated(
        sort: String?,
        name: String?
    ) = viewModelScope.launch {
        _characterState.value = _characterState.value.copy(isInProgress = true)
        try {
            _characterState.value = _characterState.value.copy(
                isInProgress = false,
                currentError = null,
                successModel = getFilteredCharacterPaginated(sort, name)
                    .cachedIn(viewModelScope)
            )
        } catch (e: Exception) {
            _characterState.value = _characterState.value.copy(
                isInProgress = false,
                currentError = e,
                successModel = null
            )
        }
    }

    fun toggleFavorite(
        slug : String,
        characterLightModel: CharacterLightModel
    )  {
        viewModelScope.launch {
            Log.d("CharacterViewModel", "Toggle favorite character with slug: $slug")
            _toggleFavoriteState.value = _toggleFavoriteState.value.copy(isInProgress = true)
            try {
                _toggleFavoriteState.value = _toggleFavoriteState.value.copy(
                    isInProgress = false,
                    currentError = null,
                    isCharacterFavorite = toggleFavoriteCharacter(slug = slug, characterLightModel =  characterLightModel).first()
                )
            } catch (e: Exception) {
                _toggleFavoriteState.value = _toggleFavoriteState.value.copy(
                    isInProgress = false,
                    currentError = e
                )
            }
        }
    }

    private fun fetchFavoritesCharacters() = viewModelScope.launch {
        getFavoritesCharacters()
            .catch { e ->
                _favoriteState.value = _favoriteState.value.copy(
                    isInProgress = false,
                    currentError = e.message?.let { Exception(it) }
                )
            }
            .collectLatest { exists ->
                _favoriteState.value = FavoriteState(
                    isInProgress = false,
                    currentError = null,
                    successModel =exists
                )
            }

    }
}
