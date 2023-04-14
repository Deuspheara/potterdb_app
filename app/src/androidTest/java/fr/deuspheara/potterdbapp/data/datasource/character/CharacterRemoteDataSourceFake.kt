package fr.deuspheara.potterdbapp.data.datasource.character

import androidx.paging.PagingSource
import com.google.gson.Gson
import fr.deuspheara.potterdbapp.TestingModelProvider
import fr.deuspheara.potterdbapp.core.coroutine.DispatcherModule
import fr.deuspheara.potterdbapp.data.datasource.CharacterRemoteDataSource
import fr.deuspheara.potterdbapp.data.network.api.CharacterApi
import fr.deuspheara.potterdbapp.data.network.model.CharacterType
import fr.deuspheara.potterdbapp.data.paging.CharacterPagingSourceFake
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class CharacterRemoteDataSourceFake @Inject constructor(
) : CharacterRemoteDataSource {

    override suspend fun getCharacter(slug: String): CharacterType.PotterCharacter {
        return TestingModelProvider.providePotterCharacter()
    }

    override fun createCharacterPagingSource(
        sort: String?,
        name: String?
    ): PagingSource<Int, CharacterType> {
        return CharacterPagingSourceFake()
    }


}
