package fr.deuspheara.potterdbapp.data.network.api

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import fr.deuspheara.potterdbapp.TestingModelProvider
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import mockwebserver3.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

//@HiltAndroidTest
//internal class CharacterApiTest {
//
//    @get:Rule
//    val hiltAndroidRule = HiltAndroidRule(this)
//
//    @Inject
//    lateinit var characterApi: CharacterApi
//
//    @Inject
//    lateinit var mockWebServer: MockWebServer
//
//    @Before
//    fun setUp() {
//        hiltAndroidRule.inject()
//        mockWebServer.start()
//    }
//
//    @After
//    fun tearDown() {
//        mockWebServer.shutdown()
//    }
//
//    @Test
//    fun getCharacterBySlug() = runTest {
//        val expected = TestingModelProvider.provideCharacterResponse()
//        val actual = characterApi.getCharacter("harry-potter").body()
//        assertEquals(expected, actual)
//    }
//
//    @Test
//    fun getCharacters() = runTest {
//        val expected = TestingModelProvider.provideMultipleCharacterResponse()
//        val actual = characterApi.getCharacters("name", "Harry", 1, 5).body()
//        assertEquals(expected, actual)
//    }
//}