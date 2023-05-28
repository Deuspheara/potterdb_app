package fr.deuspheara.potterdbapp.data.repository.character

import androidx.paging.testing.asSnapshot
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import fr.deuspheara.potterdbapp.TestingModelProvider
import fr.deuspheara.potterdbapp.TestingModelProvider.provideCharacterTypeWithId
import fr.deuspheara.potterdbapp.data.network.model.CharacterType
import fr.deuspheara.potterdbapp.data.repository.CharacterRepository
import junit.framework.TestCase.assertEquals
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

    private lateinit var expectedCharacter: CharacterType.PotterCharacter
    private lateinit var expectedFilteredCharacters: List<CharacterType>

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
        expectedCharacter = TestingModelProvider.providePotterCharacter()
        expectedFilteredCharacters = listOf(
            provideCharacterTypeWithId(""),
            provideCharacterTypeWithId(""),

        )
    }

    @Test
    fun getCharacter() = runTest {
        val actualCharacter = characterRepository.getCharacter("harry-potter")
        assertEquals(expectedCharacter, actualCharacter)
    }

    @Test
    fun getFilteredCharacterPaginated() = runTest {

        val actual : List<CharacterType> = characterRepository.getFilteredCharacterPaginated(null, null).asSnapshot(
            coroutineScope = this,
        ){
            scrollTo(1)
        }

        assertEquals(expectedFilteredCharacters, actual)
    }
}
