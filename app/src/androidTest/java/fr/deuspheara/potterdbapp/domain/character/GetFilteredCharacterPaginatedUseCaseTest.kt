package fr.deuspheara.potterdbapp.domain.character

import androidx.paging.PagingData

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import fr.deuspheara.potterdbapp.TestingModelProvider
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import mockwebserver3.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class GetFilteredCharacterPaginatedUseCaseTest {
    private val mockWebServer = MockWebServer()

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var getFilteredCharacterPaginatedUseCase: GetFilteredCharacterPaginatedUseCase

    @Before
    fun setUp(){
        hiltAndroidRule.inject()
    }

    @Test
    fun invoke() = runTest {
//        val expected = PagingData.from(listOf(TestingModelProvider.provideCharacterTypeWithId("1")))
//        val actual = getFilteredCharacterPaginatedUseCase("name", "Harry")
//            .take(1)
//            .single()
//        assertEquals(expected, actual)
    }
}