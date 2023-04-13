package fr.deuspheara.potterdbapp.data.network.model

import com.google.gson.annotations.SerializedName

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