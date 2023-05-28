package fr.deuspheara.potterdbapp.data.network.model

import com.google.gson.annotations.SerializedName

class CharacterLightModel (
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("species")
    val species: String?,
    @SerializedName("gender")
    val gender : String?,
    @SerializedName("house")
    val house : String?,
    @SerializedName("born")
    val born: String?,
){
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is CharacterLightModel) {
            return false
        }

        return this.slug == other.slug &&
                this.name == other.name &&
                this.image == other.image &&
                this.species == other.species &&
                this.gender == other.gender &&
                this.house == other.house &&
                this.born == other.born
    }
}