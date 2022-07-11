package com.ozge.restaurantz.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ozge.restaurantz.data.paging_source.GetRestaurantsSource
import com.ozge.restaurantz.domain.model.RestaurantUIModel
import com.ozge.restaurantz.domain.repository.RestaurantRepository
import com.ozge.restaurantz.utils.ApiConstants.DEFAULT_PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {

    val restaurants: Flow<PagingData<RestaurantUIModel>> =
        Pager(PagingConfig(pageSize = DEFAULT_PAGE_SIZE)) {
            GetRestaurantsSource(restaurantRepository)
        }.flow

}