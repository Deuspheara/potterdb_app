package fr.deuspheara.potterdbapp.data.network.api

import fr.deuspheara.potterdbapp.data.network.model.CharacterResponse
import fr.deuspheara.potterdbapp.data.network.model.CharacterType
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {
    @GET("characters")
    suspend fun getCharacters(
        @Query("sort") sort: String?,
        @Query("filter[name_cont_any]") name: String?,
        @Query("page[number]") pageNumber: Int?,
        @Query("page[size]") pageSize: Int?
    ): Response<CharacterResponse<List<CharacterType>>>

    @GET("characters/{id}")
    suspend fun getCharacter(
        @Path("id") slug: String?
    ): Response<CharacterResponse<CharacterType>>
}