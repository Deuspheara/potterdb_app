package fr.deuspheara.potterdbapp.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.gson.Gson
import fr.deuspheara.potterdbapp.core.coroutine.DispatcherModule
import fr.deuspheara.potterdbapp.data.network.model.PotterResponse
import fr.deuspheara.potterdbapp.data.network.model.CharacterType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

/**
* This class is used to create a paging source
*
* @param apiCall: suspend (sort: String?, name: String?, pageNumber: Int?, pageSize: Int?) -> Response<CharacterResponse<List<CharacterType>>> - api call
* @param dispatcher: CoroutineDispatcher - dispatcher
*
* @return PagingSource<Int, PotterCharacter> - paging source
 */
class ApiPagingCharacter @Inject constructor(
    private val apiCall: suspend (sort: String?, name: String?, pageNumber: Int?, pageSize: Int?) -> Response<PotterResponse<List<CharacterType>>>,
    @DispatcherModule.DispatcherIO private val dispatcher: CoroutineDispatcher,
    private val gson: Gson,
    private val sort: String?,
    private val name: String?
) : PagingSource<Int, CharacterType>() {

    private companion object {
        private const val INITIAL_PAGE_NUMBER = 1
        private const val PAGE_SIZE = 20
        private const val TAG = "ApiPagingCharacter"
    }

    /**
     * This method is used to load the next page
     * @param params: LoadParams<Int> - load params
     *
     * @return LoadResult<Int, PotterCharacter> - load result
     *
     * @throws Exception
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterType> {
        return withContext(dispatcher) {
            try {
                val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
                val response = apiCall(sort, name, pageNumber, PAGE_SIZE)
                if (response.isSuccessful) {
                    val potterResponse = response.body()
                    LoadResult.Page(
                        data = potterResponse?.data ?: emptyList(),
                        prevKey = if (pageNumber == INITIAL_PAGE_NUMBER) null else pageNumber - 1,
                        nextKey = if (potterResponse?.data?.isEmpty() == true) null else pageNumber + 1
                    )
                } else {
                    LoadResult.Error(Exception("Error while fetching characters with sort $sort and name $name"))
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error while fetching characters with sort $sort and name $name", e)
                LoadResult.Error(e)
            }
        }
    }


    /**
     * This method is used to get the refresh key
     *
     * @param state: PagingState<Int, PotterCharacter> - paging state
     *
     * @return Int? - refresh key
     */
    override fun getRefreshKey(state: PagingState<Int, CharacterType>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
