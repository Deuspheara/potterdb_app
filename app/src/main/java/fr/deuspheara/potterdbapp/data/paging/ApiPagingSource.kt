package fr.deuspheara.potterdbapp.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import fr.deuspheara.potterdbapp.core.coroutine.DispatcherModule
import fr.deuspheara.potterdbapp.data.network.model.PotterCharacter
import fr.deuspheara.potterdbapp.data.network.model.PotterResponse
import fr.deuspheara.potterdbapp.data.network.model.PotterType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

/*
* This class is used to create a paging source
*
* @param apiCall: suspend (sort: String?, name: String?, pageNumber: Int?, pageSize: Int?) -> Response<PotterResponse<PotterCharacter>> - api call
* @param dispatcher: CoroutineDispatcher - dispatcher
*
* @return PagingSource<Int, PotterCharacter> - paging source
 */
class ApiPagingCharacter @Inject constructor(
    private val apiCall: suspend (sort: String?, name: String?, pageNumber: Int?, pageSize: Int?) -> Response<PotterResponse<PotterCharacter>>,
    @DispatcherModule.DispatcherIO private val dispatcher: CoroutineDispatcher,
    private val moshi: Moshi,
    private val sort: String?,
    private val name: String?
) : PagingSource<Int, PotterCharacter>() {

    private companion object {
        private const val INITIAL_PAGE_NUMBER = 1
        private const val PAGE_SIZE = 20
        private const val TAG = "ApiPagingCharacter"
    }

    /**
     * This method is used to get the refresh key
     * @param state: PagingState<Int, PotterCharacter> - paging state
     * @return Int? - refresh key
     * @throws Exception
     */
    private fun parsePotterResponse(response: String, moshi: Moshi): PotterResponse<PotterCharacter>? {
        val type = Types.newParameterizedType(PotterResponse::class.java, PotterType::class.java)
        val adapter: JsonAdapter<PotterResponse<PotterCharacter>> = moshi.adapter(type)
        return adapter.fromJson(response)
    }

    /**
     * This method is used to load the next page
     * @param params: LoadParams<Int> - load params
     * @return LoadResult<Int, PotterCharacter> - load result
     * @throws Exception
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PotterCharacter> {
        return withContext(dispatcher) {
            try {
                val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
                val response = apiCall(sort, name, pageNumber, PAGE_SIZE)
                if (response.isSuccessful) {
                    val potterResponse = parsePotterResponse(response.body().toString(), moshi)
                    LoadResult.Page(
                        data = potterResponse?.data?.attributes ?: emptyList(),
                        prevKey = if (pageNumber == INITIAL_PAGE_NUMBER) null else pageNumber - 1,
                        nextKey = if (potterResponse?.data?.attributes?.isEmpty() == true) null else pageNumber + 1
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
     * @param state: PagingState<Int, PotterCharacter> - paging state
     * @return Int? - refresh key
     */
    override fun getRefreshKey(state: PagingState<Int, PotterCharacter>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
