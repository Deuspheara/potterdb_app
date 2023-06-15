package fr.deuspheara.potterdbapp.data.repository.character

import androidx.paging.testing.asSnapshot
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import fr.deuspheara.potterdbapp.TestingModelProvider
import fr.deuspheara.potterdbapp.TestingModelProvider.provideCharacterTypeWithId
import fr.deuspheara.potterdbapp.core.coroutine.DispatcherModule
import fr.deuspheara.potterdbapp.data.network.mapper.toCharacterLight
import fr.deuspheara.potterdbapp.core.model.character.CharacterLightModel
import fr.deuspheara.potterdbapp.data.network.model.CharacterType
import fr.deuspheara.potterdbapp.data.repository.CharacterRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class CharacterRepositoryTestImpl {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var characterRepository: CharacterRepository

    @Inject
    @DispatcherModule.DispatcherIO
    lateinit var dispatcher: CoroutineDispatcher

    private lateinit var expectedCharacter: CharacterLightModel
    private lateinit var expectedFilteredCharacters: List<CharacterLightModel>

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
        expectedCharacter = TestingModelProvider.providePotterCharacter().toCharacterLight()
        expectedFilteredCharacters = listOf(
            provideCharacterTypeWithId("").toCharacterLight(),
            provideCharacterTypeWithId("").toCharacterLight(),

        )
    }

    @Test
    fun getCharacter() = runTest(dispatcher) {
        val actualCharacter = characterRepository.getCharacter("harry-potter")
        assertEquals(expectedCharacter, actualCharacter)
    }

    @Test
    fun getFilteredCharacterPaginated() = runTest(dispatcher) {

        val actual : List<CharacterLightModel> = characterRepository.getFilteredCharacterPaginated(null, null).asSnapshot(){
            scrollTo(1)
        }

        assertEquals(expectedFilteredCharacters, actual)
    }

    @Test
    fun toggleFavoriteCharacter() = runTest(dispatcher) {
        val expected = true
        val actual = characterRepository.toggleFavoriteCharacter("harry-potter", TestingModelProvider.provideCharacterLightModel())
        assertEquals(expected, actual)
    }
}
