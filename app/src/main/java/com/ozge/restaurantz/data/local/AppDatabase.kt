package com.ozge.restaurantz.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ozge.restaurantz.data.entity.RestaurantEntity
import com.ozge.restaurantz.utils.DatabaseConstants

@Database(
    entities = [RestaurantEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun restaurantDao(): RestaurantDao

    companion object {
        fun create(context: Context, useInMemory: Boolean = false): AppDatabase {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            } else {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DatabaseConstants.DATABASE_NAME
                )
            }
            return databaseBuilder.fallbackToDestructiveMigration().build()
        }
    }
}