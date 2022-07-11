package com.ozge.restaurantz.di

import android.content.Context
import com.ozge.restaurantz.data.local.AppDatabase
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
    fun provideDatabase(@ApplicationContext context: Context) = AppDatabase.create(context)

    @Provides
    @Singleton
    fun provideRestaurantDao(appDatabase: AppDatabase) = appDatabase.restaurantDao()

}