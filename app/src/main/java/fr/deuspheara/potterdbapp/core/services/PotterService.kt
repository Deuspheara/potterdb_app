package fr.deuspheara.potterdbapp.core.services

import fr.deuspheara.potterdbapp.data.model.PotterCharacter
import fr.deuspheara.potterdbapp.data.model.PotterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PotterService {
    @GET("characters")
    fun getCharacters(
        @Query("sort") sort: String?,
        @Query("filter[name_cont_any]") name: String?,
        @Query("page[number]") pageNumber: Int?,
        @Query("page[size]") pageSize: Int?
    ): Response<PotterResponse<PotterCharacter>>
}