package com.ozge.restaurantz.data.repository

import com.ozge.restaurantz.data.local.RestaurantDao
import com.ozge.restaurantz.data.mapper.RestaurantUIMapper
import com.ozge.restaurantz.data.remote.RestaurantApi
import com.ozge.restaurantz.domain.model.RestaurantUIModel
import com.ozge.restaurantz.domain.repository.RestaurantRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RestaurantRepositoryImpl @Inject constructor(
    private val api: RestaurantApi,
    private val mapper: RestaurantUIMapper,
    private val restaurantDao: RestaurantDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RestaurantRepository {

    override suspend fun getRestaurants(
        size: Int,
        page: Int
    ): List<RestaurantUIModel>? = withContext(ioDispatcher) {
        val apiResult = api.getRestaurants(page = page, size = size)
        if (apiResult.isSuccessful && (apiResult.body()?.isEmpty() == false)) {
            restaurantDao.addRestaurants(apiResult.body()!!)
            val mappedData = apiResult.body()?.map {
                mapper.mapToUIModel(it)
            }
            return@withContext mappedData
        } else {
            return@withContext emptyList()
        }
    }
}