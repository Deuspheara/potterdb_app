package fr.deuspheara.potterdbapp.data.network.dispatcher

import android.util.Log
import com.google.gson.Gson
import fr.deuspheara.potterdbapp.TestingModelProvider
import mockwebserver3.Dispatcher
import mockwebserver3.MockResponse
import mockwebserver3.RecordedRequest
import org.junit.Test
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection

class TestingCallDispatcher @Inject constructor() : Dispatcher() {

    companion object {
        private const val TAG: String = "TestingCallDispatcher"
    }

    override fun dispatch(request: RecordedRequest): MockResponse {
        Log.e(TAG, "dispatch ${request.path}")
        return when (request.method){
            "GET" -> createGetResponse(request)
            else -> MockResponse().apply {
                this.setResponseCode(HttpsURLConnection.HTTP_INTERNAL_ERROR)
                this.setBody("Unknown method")
            }
        }
    }

    private fun createGetResponse(request : RecordedRequest) : MockResponse {
        return when (val path = request.path
            ?.removePrefix("/")
            ?.replaceAfter('?', String())
            ?.removeSuffix("?")
        ) {
            "characters" -> {
                MockResponse().apply {
                    setResponseCode(HttpsURLConnection.HTTP_OK)
                    setBody(Gson().toJson(TestingModelProvider.provideMultipleCharacterResponse()))
                }
            }
            "characters/harry-potter" -> {
                request.body.readUtf8()
                MockResponse().apply {
                    this.setResponseCode(HttpsURLConnection.HTTP_OK)
                    this.setBody(Gson().toJson(TestingModelProvider.provideCharacterResponse()))
                }
            }
            else -> MockResponse().apply {
                this.setResponseCode(HttpsURLConnection.HTTP_NOT_FOUND)
                this.setBody("Unknown path : $path")
            }
        }
    }
}