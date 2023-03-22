package com.kenstarry.harrypotter.core.domain.use_case

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.kenstarry.harrypotter.core.data.HarryPotterApi
import com.kenstarry.harrypotter.core.data.MyInterceptor
import com.kenstarry.harrypotter.core.presentation.util.Constants
import com.kenstarry.harrypotter.core.presentation.util.Constants.Companion.BASE_URL
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import util.MockResponseFileReader
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

class GetCharactersTest {

    private val mockWebServer = MockWebServer()
    private lateinit var characters: GetCharacters
    private lateinit var mockedResponse: String
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    @Before
    fun init() {
        mockWebServer.start(8000)
        //  our http3 client
        val client = OkHttpClient
            .Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(MyInterceptor())
            .build()

        //  our mock api
        val api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/").toString())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(HarryPotterApi::class.java)

        characters = GetCharacters(api)
    }

    @Test
    fun `check that the api doesn't return null`() {

        mockedResponse = MockResponseFileReader("hp_api/harry_potter.json").content

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockedResponse)
        )

        val response = runBlocking { characters.invoke() }

        Assert.assertNotNull(response)
    }

    @Test
    fun `Should fetch all harry potter characters - 200 response`() {

        mockedResponse = MockResponseFileReader("hp_api/harry_potter.json").content

        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(mockedResponse)
        )

        val response = runBlocking { characters.invoke() }
        val json = gson.toJson(response.body())
        val resultResponse = JsonParser().parse(json)
        val expectedResponse = JsonParser().parse(mockedResponse)

        Assert.assertTrue(resultResponse.equals(expectedResponse))
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

}


























