package fr.deuspheara.potterdbapp.domain.character

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import fr.deuspheara.potterdbapp.TestingModelProvider
import fr.deuspheara.potterdbapp.data.network.mapper.toCharacterLight
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class GetFavoriteCharacterUseCaseTest {
    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var getFavoritesCharactersUseCase: GetFavoritesCharactersUseCase

    @Before
    fun setUp(){
        hiltAndroidRule.inject()
    }

    @Test
    fun invoke() = runTest {
        val expected = listOf(
            TestingModelProvider.providePotterCharacter().toCharacterLight(),
            TestingModelProvider.providePotterCharacter().toCharacterLight()
        )

        val actual = getFavoritesCharactersUseCase().first()
        TestCase.assertEquals(expected, actual)
    }
}