package com.ozge.restaurantz.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.ozge.restaurantz.data.remote.RestaurantApi
import com.ozge.restaurantz.utils.ApiConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient() = OkHttpClient.Builder()
        .readTimeout(ApiConstants.CLIENT_TIMEOUT, TimeUnit.MINUTES)
        .connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.MINUTES)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .client(client)
        .addConverterFactory(Json.asConverterFactory(ApiConstants.MEDIA_TYPE.toMediaType()))

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) = retrofit.create(RestaurantApi::class.java)

}