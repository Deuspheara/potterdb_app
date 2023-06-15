package fr.deuspheara.potterdbapp.domain.character

import androidx.paging.PagingData
import androidx.paging.map

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import fr.deuspheara.potterdbapp.TestingModelProvider
import fr.deuspheara.potterdbapp.data.network.mapper.toCharacterLight
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
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
        val expected = TestingModelProvider.provideCharacterTypeWithId("1").toCharacterLight()

        val actual = getFilteredCharacterPaginatedUseCase("name", "Harry").take(1).first().map { actual ->
            assertEquals(expected, actual)
        }
    }
}