package fr.deuspheara.potterdbapp.data.datasource.character.local

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import fr.deuspheara.potterdbapp.TestingModelProvider
import fr.deuspheara.potterdbapp.core.coroutine.DispatcherModule
import fr.deuspheara.potterdbapp.data.database.model.CharacterEntity
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class CharacterLocalDataSourceImplTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var dataSource: CharacterLocalDataSource

    @Inject
    @DispatcherModule.DispatcherIO
    lateinit var dispatcher: CoroutineDispatcher

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun insertCharacterIfNotExist() = runTest(dispatcher) {
        val expected : Long = 0
        val actual =
            dataSource.insertCharacterIfNotExist(TestingModelProvider.provideCharacterEntity())

        assertEquals(expected, actual)
    }

    @Test
    fun insertAllCharacters() = runTest(dispatcher) {
        val expected: List<Long> = listOf(0, 1, 2)
        val actual = dataSource.insertAllCharacters(
            listOf(
                TestingModelProvider.provideCharacterEntity()
            )
        )
        assertEquals(expected, actual)
    }

    @Test
    fun getAllCharacter() = runTest(dispatcher){
        val expected : List<CharacterEntity> = listOf(TestingModelProvider.provideCharacterEntity())
        val actual = dataSource.getAllCharacters().first()
        assertEquals(expected,actual)
    }

    @Test
    fun getCharacterBySlug() = runTest(dispatcher) {
        val expected = TestingModelProvider.provideCharacterEntity()
        val actual = dataSource.getCharacterBySlug("harry-potter")
        assertEquals(expected, actual)
    }
}