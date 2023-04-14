package fr.deuspheara.potterdbapp.data.network.model

import com.google.gson.annotations.SerializedName

data class CharacterType(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("attributes")
    val attributes: PotterCharacter
){
    data class PotterCharacter(
        @SerializedName("slug")
        val slug: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("born")
        val born: String?,
        @SerializedName("died")
        val died: String?,
        @SerializedName("gender")
        val gender: String?,
        @SerializedName("species")
        val species: String?,
        @SerializedName("height")
        val height: String?,
        @SerializedName("weight")
        val weight: String?,
        @SerializedName("hair_color")
        val hairColor: String?,
        @SerializedName("eye_color")
        val eyeColor: String?,
        @SerializedName("skin_color")
        val skinColor: String?,
        @SerializedName("blood_status")
        val bloodStatus: String?,
        @SerializedName("nationality")
        val nationality: String?,
        @SerializedName("animagus")
        val animagus: String?,
        @SerializedName("boggart")
        val boggart: String?,
        @SerializedName("house")
        val house: String?,
        @SerializedName("patronus")
        val patronus: String?,
        @SerializedName("alias_names")
        val aliasNames: List<String>?,
        @SerializedName("family_member")
        val familyMembers: List<String>?,
        @SerializedName("jobs")
        val jobs: List<String>?,
        @SerializedName("romances")
        val romances: List<String>?,
        @SerializedName("titles")
        val titles: List<String>?,
        @SerializedName("wands")
        val wands: List<String>?,
        @SerializedName("image")
        val image: String?,
        @SerializedName("wiki")
        val wiki: String?
    )
}