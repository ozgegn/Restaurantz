package com.ozge.restaurantz.ui.screens.detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozge.restaurantz.domain.model.RestaurantUIModel
import com.ozge.restaurantz.domain.repository.RestaurantRepository
import com.ozge.restaurantz.utils.NavConstants.KEY_RESTAURANT_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent: SharedFlow<UiEvent> = _uiEvent.asSharedFlow()

    private val _colorPalette: MutableState<Map<String, String>> = mutableStateOf(mapOf())
    val colorPalette: State<Map<String, String>> = _colorPalette

    private val _restaurantDetail = MutableStateFlow<RestaurantUIModel?>(null)
    val restaurantDetail: StateFlow<RestaurantUIModel?>
        get() = _restaurantDetail

    init {
        viewModelScope.launch {
            savedStateHandle.get<Int>(KEY_RESTAURANT_ID)?.let {
                _restaurantDetail.value = restaurantRepository.getRestaurant(it)
            }
        }
    }

    fun generateColorPalette() {
        viewModelScope.launch {
            _uiEvent.emit(UiEvent.GenerateColorPalette)
        }
    }

    fun setColorPalette(colors: Map<String, String>) {
        _colorPalette.value = colors
    }

}

sealed class UiEvent {
    object GenerateColorPalette : UiEvent()
}