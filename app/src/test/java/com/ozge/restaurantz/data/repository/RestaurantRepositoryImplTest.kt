package com.ozge.restaurantz.data.repository

import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.ozge.restaurantz.data.local.RestaurantDao
import com.ozge.restaurantz.data.mapper.RestaurantUIMapper
import com.ozge.restaurantz.data.remote.RestaurantApi
import com.ozge.restaurantz.domain.repository.RestaurantRepository
import com.ozge.restaurantz.utils.MainCoroutinesRule
import com.ozge.restaurantz.utils.restaurant1
import com.ozge.restaurantz.utils.restaurantList
import junit.framework.Assert.assertNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class RestaurantRepositoryImplTest {

    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    private val api: RestaurantApi = mock()
    private val mapper = RestaurantUIMapper()
    private val restaurantDao: RestaurantDao = mock()
    lateinit var restaurantRepository: RestaurantRepository


    @Before
    fun setUp() {
        restaurantRepository = RestaurantRepositoryImpl(
            api, mapper, restaurantDao, coroutinesRule.testDispatcher
        )
    }

    @Test
    fun getRestaurantsCallsApiSuccessfully() = runTest {
        whenever(api.getRestaurants()).thenReturn(Response.success(restaurantList))
        restaurantRepository.getRestaurants(page = 1, size = 5)
        verify(api, atLeastOnce()).getRestaurants()
    }

    @Test
    fun getRestaurantsReturnsListWithItemsSuccessfully() = runTest {
        whenever(api.getRestaurants()).thenReturn(Response.success(restaurantList))
        val result = restaurantRepository.getRestaurants(page = 1, size = 5)
        requireNotNull(result)
        val expected = mapper.mapToUIModel(restaurant1)
        assertThat(result[0].toString(), `is`(expected.toString()))
    }

    @Test
    fun getRestaurantsGetsListAndAddSuccessfully() = runTest {
        whenever(api.getRestaurants()).thenReturn(Response.success(restaurantList))
        restaurantRepository.getRestaurants(page = 1, size = 5)
        verify(restaurantDao, atLeastOnce()).addRestaurants(restaurantList)
    }

    @Test
    fun getRestaurantsReturnsEmptyListSuccessfully() = runTest {
        whenever(api.getRestaurants()).thenReturn(Response.success(emptyList()))
        val result = restaurantRepository.getRestaurants(page = 1, size = 5)
        requireNotNull(result)
        assertThat(result.size, `is`(0))
    }

    @Test
    fun getRestaurantsReturnsEmptyListWhenGettingError() = runTest {
        whenever(api.getRestaurants()).thenThrow(RuntimeException("No connection"))
        val result = restaurantRepository.getRestaurants(page = 1, size = 5)
        requireNotNull(result)
        assertThat(result.size, `is`(0))
    }

    @Test
    fun getRestaurantReturnsSelectedRestaurantSuccessfully() = runTest {
        whenever(restaurantDao.getRestaurant(5818)).thenReturn(restaurant1)
        val result = restaurantRepository.getRestaurant(5818)
        requireNotNull(result)
        val expected = mapper.mapToUIModel(restaurant1)
        assertThat(result, `is`(expected))
    }

    @Test
    fun getRestaurantReturnsNullOnError() = runTest {
        whenever(restaurantDao.getRestaurant(5818)).thenThrow(RuntimeException("No data found"))
        val result = restaurantRepository.getRestaurant(5818)
        assertNull(result)
    }
}