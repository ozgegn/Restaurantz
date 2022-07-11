package com.ozge.restaurantz.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ozge.restaurantz.domain.model.Resource
import com.ozge.restaurantz.domain.model.RestaurantUIModel
import com.ozge.restaurantz.domain.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {

    private val _restaurants = MutableLiveData<Resource<List<RestaurantUIModel>>>()
    val restaurants: LiveData<Resource<List<RestaurantUIModel>>>
        get() = _restaurants

    init {
        getAllRestaurants()
    }

    fun getAllRestaurants() {
        viewModelScope.launch {
            _restaurants.value = restaurantRepository.getRestaurants(10)
        }
    }

}