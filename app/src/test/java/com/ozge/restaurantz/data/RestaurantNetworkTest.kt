package com.ozge.restaurantz.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.ozge.restaurantz.data.remote.RestaurantApi
import com.ozge.restaurantz.utils.ApiConstants
import com.ozge.restaurantz.utils.MainCoroutinesRule
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit

@ExperimentalSerializationApi
@RunWith(JUnit4::class)
class RestaurantNetworkTest {

    @Rule
    @JvmField
    val instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesRule = MainCoroutinesRule()

    val mockWebServer: MockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val jsonConfig = Json {
        ignoreUnknownKeys = true
    }

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(
            jsonConfig.asConverterFactory(ApiConstants.MEDIA_TYPE.toMediaType())
        ).build().create(RestaurantApi::class.java)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun fetchRestaurantListFromApiReturnsSuccess() = runBlocking {
        enqueueResponse()
        val response = api.getRestaurants()
        assertTrue(response.isSuccessful)
        requireNotNull(response.body())
        mockWebServer.takeRequest()
        assertThat(response.body()!!.size, `is`(2))
        assertThat(response.body()!!.get(0).name, `is`("Golden Pub"))
    }

    @Throws(IOException::class)
    private fun enqueueResponse() {
        val inputStream =
            javaClass.classLoader!!.getResourceAsStream("api-response/RestaurantApiResponse.json")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockWebServer.enqueue(mockResponse.setBody(source.readString(StandardCharsets.UTF_8)))
    }
}