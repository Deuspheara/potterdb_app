package fr.deuspheara.potterdbapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import fr.deuspheara.potterdbapp.TestingModelProvider
import fr.deuspheara.potterdbapp.data.network.mapper.toCharacterLight
import fr.deuspheara.potterdbapp.data.network.model.CharacterType
import javax.inject.Inject

class CharacterPagingSourceFake @Inject constructor(): PagingSource<Int, CharacterType>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterType>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterType> {
        return try {
            val page = params.key ?: 1
            val characters = List(2) {
                TestingModelProvider.provideCharacterTypeWithId("")
                TestingModelProvider.provideCharacterTypeWithId("")
            }
            LoadResult.Page(
                data = characters,
                prevKey = if (page == 1) null else page - 1,
                nextKey = page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}