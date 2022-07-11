package com.ozge.restaurantz.di

import com.ozge.restaurantz.data.local.RestaurantDao
import com.ozge.restaurantz.data.mapper.RestaurantUIMapper
import com.ozge.restaurantz.data.remote.RestaurantApi
import com.ozge.restaurantz.data.repository.RestaurantRepositoryImpl
import com.ozge.restaurantz.domain.repository.RestaurantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRestaurantUIMapper(): RestaurantUIMapper = RestaurantUIMapper()

    @Provides
    fun provideRestaurantRepository(
        api: RestaurantApi,
        mapper: RestaurantUIMapper,
        dao: RestaurantDao
    ): RestaurantRepository = RestaurantRepositoryImpl(
        api = api,
        mapper = mapper,
        restaurantDao = dao
    )

}