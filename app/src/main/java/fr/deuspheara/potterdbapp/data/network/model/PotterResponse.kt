package fr.deuspheara.potterdbapp.data.network.model

import com.google.gson.annotations.SerializedName

/** Class representing a character response from the API */
data class PotterResponse<T>(
    @SerializedName("data")
    val data: T,
    @SerializedName("meta")
    val meta: PotterMeta,
    @SerializedName("links")
    val links: PotterLinks
)


