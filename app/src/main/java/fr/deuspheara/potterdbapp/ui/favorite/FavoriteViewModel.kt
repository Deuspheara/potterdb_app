package fr.deuspheara.potterdbapp.ui.favorite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.deuspheara.potterdbapp.core.coroutine.DispatcherModule
import fr.deuspheara.potterdbapp.core.model.character.CharacterLightModel
import fr.deuspheara.potterdbapp.domain.character.GetFavoritesCharactersUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

data class FavoriteState(
    val isInProgress: Boolean,
    val currentError: Exception?,
    val successModel: List<CharacterLightModel>? = null
)

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoritesCharacters: GetFavoritesCharactersUseCase
) : ViewModel() {

    private val _favoriteState = MutableStateFlow(
        FavoriteState(
            isInProgress = false,
            currentError = null,
            successModel = null
        )
    )

    val favoriteState: StateFlow<FavoriteState> = _favoriteState.asStateFlow()

    init {
        fetchFavoritesCharacters()
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