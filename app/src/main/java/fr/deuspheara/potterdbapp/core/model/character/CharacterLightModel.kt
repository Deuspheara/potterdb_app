package fr.deuspheara.potterdbapp.core.model.character

import com.google.gson.annotations.SerializedName

class CharacterLightModel(
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("species")
    val species: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("house")
    val house: String?,
    @SerializedName("born")
    val born: String?,
    @SerializedName("height")
    val height: String?,
    @SerializedName("weight")
    val weight: String?,
){

    var isFavorite: Boolean = false
    companion object {
        fun fromFullModel(fullModel: CharacterFullModel): CharacterLightModel {
            return CharacterLightModel(
                slug = fullModel.slug,
                name = fullModel.name,
                image = fullModel.image,
                species = fullModel.species,
                gender = fullModel.gender,
                house = fullModel.house,
                born = fullModel.born,
                height = fullModel.height,
                weight = fullModel.weight
            )
        }
    }

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
                this.born == other.born &&
                this.height == other.height &&
                this.weight == other.weight
    }
}