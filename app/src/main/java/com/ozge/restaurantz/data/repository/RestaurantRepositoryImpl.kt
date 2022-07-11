package com.ozge.restaurantz.data.repository

import com.ozge.restaurantz.data.mapper.RestaurantUIMapper
import com.ozge.restaurantz.data.remote.RestaurantApi
import com.ozge.restaurantz.domain.model.Error
import com.ozge.restaurantz.domain.model.Resource
import com.ozge.restaurantz.domain.model.RestaurantUIModel
import com.ozge.restaurantz.domain.repository.RestaurantRepository
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RestaurantRepositoryImpl @Inject constructor(
    private val api: RestaurantApi,
    private val mapper: RestaurantUIMapper,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RestaurantRepository {

    override suspend fun getRestaurants(size: Int): Resource<List<RestaurantUIModel>> =
        withContext(ioDispatcher) {
            val result = api.getRestaurants(size)
            return@withContext if (result.isSuccessful) {
                val uiModelList = result.body()?.map {
                    mapper.mapToUIModel(it)
                }
                Resource.Success(uiModelList)
            } else {
                Resource.Failure(Error())
            }
        }
}