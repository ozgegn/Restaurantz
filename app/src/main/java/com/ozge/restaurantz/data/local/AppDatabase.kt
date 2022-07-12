package com.ozge.restaurantz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ozge.restaurantz.data.entity.RestaurantEntity

@Database(
    entities = [RestaurantEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}