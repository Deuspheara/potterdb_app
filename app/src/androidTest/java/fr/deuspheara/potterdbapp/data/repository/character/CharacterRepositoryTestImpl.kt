package fr.deuspheara.potterdbapp.data.repository.character

import androidx.paging.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import fr.deuspheara.potterdbapp.TestingModelProvider
import fr.deuspheara.potterdbapp.TestingModelProvider.provideCharacterTypeWithId
import fr.deuspheara.potterdbapp.data.network.model.CharacterResponse
import fr.deuspheara.potterdbapp.data.network.model.CharacterType
import fr.deuspheara.potterdbapp.data.paging.CharacterPagingSourceFake
import fr.deuspheara.potterdbapp.data.repository.CharacterRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
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


    @Before
    fun setUp(){
        hiltAndroidRule.inject()
    }

    @Test
    fun getCharacter() = runTest {
        val expected = TestingModelProvider.providePotterCharacter()
        val actual = characterRepository.getCharacter("harry-potter")
        assertEquals(expected,actual)
    }

    @Test
    fun getFilteredCharacterPaginated() = runTest {
//        val expected = listOf(
//            provideCharacterTypeWithId("1").attributes,
//            provideCharacterTypeWithId("2").attributes,
//            provideCharacterTypeWithId("3").attributes
//        )
//        val actual = characterRepository.getFilteredCharacterPaginated(null,null).asSnapshot(
//
//        )
//        assertEquals(expected,actual)
    }





}