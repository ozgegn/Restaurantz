package com.ozge.restaurantz.data.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ozge.restaurantz.utils.restaurant1
import com.ozge.restaurantz.utils.restaurantList
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RestaurantDaoTest {

    lateinit var database: AppDatabase
    lateinit var restaurantDao: RestaurantDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).allowMainThreadQueries()
            .build()

        restaurantDao = database.restaurantDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertRestaurantListAndGetWithId() = runBlocking {
        restaurantDao.addRestaurants(restaurantList)

        val loadFromDb = restaurantDao.getRestaurant(5818)
        assertNotNull(loadFromDb)
        assertThat(loadFromDb.toString(), `is`(restaurant1.toString()))
    }
}