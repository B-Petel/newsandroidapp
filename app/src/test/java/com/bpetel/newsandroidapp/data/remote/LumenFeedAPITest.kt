package com.bpetel.newsandroidapp.data.remote

import com.bpetel.newsandroidapp.data.remote.model.ArticleListDto
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import org.junit.jupiter.api.extension.ExtendWith
import org.koin.core.context.stopKoin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tech.apter.junit.jupiter.robolectric.RobolectricExtension

@ExtendWith(RobolectricExtension::class)
class LumenFeedAPITest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: LumenFeedApi

    @BeforeEach//Using JUnit5
    fun beforeEach() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpInterceptor())
            .build()

        api = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/v1/"))//Pass any base url like this
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LumenFeedApi::class.java)
    }

    @AfterEach
    fun afterEach() {
        mockWebServer.shutdown()
        stopKoin()
    }

    @Test
    fun testGetArticles_returnArticles() = runTest {
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/response.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = api.getArticles()
        val request = mockWebServer.takeRequest()

        assertEquals("/v1/articles", request.requestUrl?.encodedPath)
        assertNotNull(request.headers["X-API-Key"])
        assertEquals(false, response.body()?.data?.isEmpty())
        assertEquals(1, response.body()?.data?.size)
    }

    @Test
    fun `getArticles, returns Success`() = runTest {
        val dto = ArticleListDto()//The object I want back as response
        val gson: Gson = GsonBuilder().create()
        val json = gson.toJson(dto)!!//Conver the object into json string using GSON
        val res = MockResponse()//Make a fake response for our server call
        res.setBody(json)//set the body of the fake response as the json string you are expecting as a response
        mockWebServer.enqueue(res)//add it in the server response queue

        val data = api.getArticles()//make the call to our fake server(as we are using fake base url)
        mockWebServer.takeRequest()//let the server take the request

        assertEquals(data.body(), dto)//the data you are getting as the call response should be same
    }
}