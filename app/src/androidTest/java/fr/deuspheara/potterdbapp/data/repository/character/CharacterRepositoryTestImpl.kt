package fr.deuspheara.potterdbapp.data.repository.character

import androidx.paging.PagingData
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import fr.deuspheara.potterdbapp.TestingModelProvider
import fr.deuspheara.potterdbapp.data.network.model.CharacterResponse
import fr.deuspheara.potterdbapp.data.network.model.CharacterType
import fr.deuspheara.potterdbapp.data.repository.CharacterRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.Flow
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
        val actual = characterRepository.getCharacter(1)
        assertEquals(expected,actual)
    }

    @Test
    fun getFilteredCharacterPaginated() = runTest {

    }


}