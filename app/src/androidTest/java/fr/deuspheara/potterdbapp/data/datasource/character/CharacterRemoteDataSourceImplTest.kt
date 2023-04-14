package fr.deuspheara.potterdbapp.data.datasource.character

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import fr.deuspheara.potterdbapp.TestingModelProvider
import fr.deuspheara.potterdbapp.data.datasource.CharacterRemoteDataSource
import fr.deuspheara.potterdbapp.data.paging.CharacterPagingSourceFake
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class CharacterRemoteDataSourceImplTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)


    @Inject
    lateinit var dataSource: CharacterRemoteDataSource

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun getCharacterWithId() = runTest {
        val expected = TestingModelProvider.providePotterCharacter()
        val actual = dataSource.getCharacter("harry-potter")
        assertEquals(expected,actual)
    }

    @Test
    fun createCharacterPagingSource() = runTest {
        val actual = dataSource.createCharacterPagingSource(null, null)
        assertTrue(actual is CharacterPagingSourceFake)
    }

}