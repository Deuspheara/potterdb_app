package fr.deuspheara.potterdbapp.data.network.api

import fr.deuspheara.potterdbapp.data.network.model.PotterCharacter
import fr.deuspheara.potterdbapp.data.network.model.PotterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {
    @GET("characters")
    suspend fun getCharacters(
        @Query("sort") sort: String?,
        @Query("filter[name_cont_any]") name: String?,
        @Query("page[number]") pageNumber: Int?,
        @Query("page[size]") pageSize: Int?
    ): Response<PotterResponse<PotterCharacter>>

    @GET("characters/{id}")
    suspend fun getCharacter(
        @Query("id") id: Int?
    ): Response<PotterResponse<PotterCharacter>>
}