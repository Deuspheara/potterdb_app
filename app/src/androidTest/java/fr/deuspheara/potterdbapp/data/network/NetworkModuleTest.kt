package fr.deuspheara.potterdbapp.data.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import fr.deuspheara.potterdbapp.data.network.api.CharacterApi
import fr.deuspheara.potterdbapp.data.network.dispatcher.TestingCallDispatcher
import mockwebserver3.MockWebServer
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
object NetworkModuleTest {

    @Provides
    fun provideMockWebServer(): MockWebServer {
        return MockWebServer().apply {
            this.dispatcher = TestingCallDispatcher()
        }
    }

    @Provides
    fun provideOkHttpClient(
        mockWebServer: MockWebServer
    ): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideRetrofit(
        mockWebServer: MockWebServer,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder().baseUrl(
            mockWebServer.url("/")
        ).addConverterFactory(
            GsonConverterFactory.create(gson)
        ).client(okHttpClient).build()
    }


    @Provides
    fun providePotterApi(
        retrofit: Retrofit
    ): CharacterApi {
        return retrofit.create()
    }
}