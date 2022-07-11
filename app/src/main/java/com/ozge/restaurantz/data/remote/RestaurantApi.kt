package com.ozge.restaurantz.data.remote

import com.ozge.restaurantz.data.entity.RestaurantEntity
import com.ozge.restaurantz.utils.ApiConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RestaurantApi {

    @GET(ApiConstants.GET_RESTAURANTS_URL)
    suspend fun getRestaurants(@Path("size") size: Int): Response<List<RestaurantEntity>>

}