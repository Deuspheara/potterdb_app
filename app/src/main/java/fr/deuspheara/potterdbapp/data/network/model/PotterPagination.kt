package fr.deuspheara.potterdbapp.data.network.model

import com.google.gson.annotations.SerializedName


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

