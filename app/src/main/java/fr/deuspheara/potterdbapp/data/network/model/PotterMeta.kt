package fr.deuspheara.potterdbapp.data.network.model

import com.google.gson.annotations.SerializedName

data class PotterMeta(
    @SerializedName("pagination")
    val pagination: PotterPagination,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("generated_at")
    val generatedAt: String
)