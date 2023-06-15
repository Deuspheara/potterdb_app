package fr.deuspheara.potterdbapp.domain.character

import android.util.Log
import androidx.paging.map
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import fr.deuspheara.potterdbapp.TestingModelProvider
import fr.deuspheara.potterdbapp.core.model.character.CharacterLightModel
import fr.deuspheara.potterdbapp.data.network.mapper.toCharacterLight
import fr.deuspheara.potterdbapp.data.repository.CharacterRepository
import junit.framework.TestCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class ToggleFavoriteCharacterUseCaseTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var toggleFavoriteCharacterUseCase: ToggleFavoriteCharacterUseCase

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun invoke() = runTest {
        val expected = TestingModelProvider.provideCharacterTypeWithId(
            "1",
        ).toCharacterLight()

        val actual = toggleFavoriteCharacterUseCase(
            "harry-potter",
            TestingModelProvider.provideCharacterLightModel()
        ).first()
    }
}