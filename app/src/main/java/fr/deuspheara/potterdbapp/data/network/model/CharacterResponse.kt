package fr.deuspheara.potterdbapp.data.network.model

import com.google.gson.annotations.SerializedName


data class CharacterResponse(
    @SerializedName("data")
    val data: List<CharacterType>,
    @SerializedName("meta")
    val meta: PotterMeta,
    @SerializedName("links")
    val links: PotterLinks
)

data class CharacterType(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("attributes")
    val attributes: PotterCharacter
)
