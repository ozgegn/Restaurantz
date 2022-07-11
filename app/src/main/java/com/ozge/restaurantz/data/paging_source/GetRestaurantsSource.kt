package com.ozge.restaurantz.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ozge.restaurantz.domain.model.RestaurantUIModel
import com.ozge.restaurantz.domain.repository.RestaurantRepository

class GetRestaurantsSource(private val restaurantRepository: RestaurantRepository) :
    PagingSource<Int, RestaurantUIModel>() {

    override fun getRefreshKey(state: PagingState<Int, RestaurantUIModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RestaurantUIModel> {
        return try {
            val nextPage = params.key ?: 1
            val result = restaurantRepository.getRestaurants(size = 5, page = nextPage)
            if (!result.isNullOrEmpty()) {
                LoadResult.Page(
                    data = result,
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = nextPage + 1
                )
            } else {
                LoadResult.Error(RuntimeException())
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}