package com.ozge.restaurantz.domain.repository

import com.ozge.restaurantz.domain.model.RestaurantUIModel

interface RestaurantRepository {

    suspend fun getRestaurants(size: Int, page: Int): List<RestaurantUIModel>?

}