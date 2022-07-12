package com.ozge.restaurantz.di

import android.content.Context
import androidx.room.Room
import com.ozge.restaurantz.data.local.AppDatabase
import com.ozge.restaurantz.utils.DatabaseConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DatabaseConstants.DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideRestaurantDao(appDatabase: AppDatabase) = appDatabase.restaurantDao()

}