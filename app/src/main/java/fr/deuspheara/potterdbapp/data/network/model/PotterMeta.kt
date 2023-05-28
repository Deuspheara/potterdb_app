package fr.deuspheara.potterdbapp.data.network.model

import com.google.gson.annotations.SerializedName

/** Class representing the meta data of a character response from the API */
data class PotterMeta(
    @SerializedName("pagination")
    val pagination: PotterPagination,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("generated_at")
    val generatedAt: String
){
    /** Class representing the pagination data of a character response from the API */
    data class PotterPagination(
        @SerializedName("current")
        val current: Int,
        @SerializedName("next")
        val next: Int?,
        @SerializedName("last")
        val last: Int,
        @SerializedName("records")
        val records: Int
    )
}
/** Class representing the links of previous, next, current and last page of a character response from the API */
data class PotterLinks(
    @SerializedName("self")
    val self: String,
    @SerializedName("current")
    val current: String,
    @SerializedName("next")
    val next: String?,
    @SerializedName("last")
    val last: String
)