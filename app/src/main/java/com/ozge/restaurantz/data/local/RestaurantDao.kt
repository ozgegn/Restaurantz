package com.ozge.restaurantz.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ozge.restaurantz.data.entity.RestaurantEntity

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurants WHERE id=:id")
    fun getRestaurant(id: Int): RestaurantEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRestaurants(restaurants: List<RestaurantEntity>)

}