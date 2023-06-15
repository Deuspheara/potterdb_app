package fr.deuspheara.potterdbapp.core.model.character

import com.google.gson.annotations.SerializedName
import fr.deuspheara.potterdbapp.data.network.model.CharacterType

data class CharacterFullModel(
    /**
     * The id of the character
     */
    @SerializedName("id")
    val id: String,

    /**
     * The type of the character
     */
    @SerializedName("type")
    val type: String,
    /**
     * The attributes of the character
     */
    @SerializedName("attributes")
    val attributes: CharacterType.PotterCharacter,
    /**
     * The slug of the character
     */
    @SerializedName("slug")
    val slug: String?,
    /**
     * The name of the character
     */
    @SerializedName("name")
    val name: String?,
    /**
     * The date of birth of the character
     */
    @SerializedName("born")
    val born: String?,
    /**
     * The date of death of the character
     */
    @SerializedName("died")
    val died: String?,
    /**
     * The gender of the character
     */
    @SerializedName("gender")
    val gender: String?,
    /**
     * The species of the character
     */
    @SerializedName("species")
    val species: String?,
    /**
     * The height of the character
     */
    @SerializedName("height")
    val height: String?,
    /**
     * The weight of the character
     */
    @SerializedName("weight")
    val weight: String?,
    /**
     * The hair color of the character
     */
    @SerializedName("hair_color")
    val hairColor: String?,
    /**
     * The eye color of the character
     */
    @SerializedName("eye_color")
    val eyeColor: String?,
    /**
     * The skin color of the character
     */
    @SerializedName("skin_color")
    val skinColor: String?,
    /**
     * The blood status of the character
     */
    @SerializedName("blood_status")
    val bloodStatus: String?,
    /**
     * The nationality of the character
     */
    @SerializedName("nationality")
    val nationality: String?,
    /**
     * The animagus of the character
     */
    @SerializedName("animagus")
    val animagus: String?,
    /**
     * The boggart of the character
     */
    @SerializedName("boggart")
    val boggart: String?,
    /**
     * The house of the character
     */
    @SerializedName("house")
    val house: String?,
    /**
     * The patronus of the character
     */
    @SerializedName("patronus")
    val patronus: String?,
    /**
     * The alias names of the character
     */
    @SerializedName("alias_names")
    val aliasNames: List<String>?,
    /**
     * The family members of the character
     */
    @SerializedName("family_member")
    val familyMembers: List<String>?,
    /**
     * The jobs of the character
     */
    @SerializedName("jobs")
    val jobs: List<String>?,
    /**
     * The romances of the character
     */
    @SerializedName("romances")
    val romances: List<String>?,
    /**
     * The titles of the character
     */
    @SerializedName("titles")
    val titles: List<String>?,
    /**
     * The wands of the character
     */
    @SerializedName("wands")
    val wands: List<String>?,
    /**
     * The images of the character
     */
    @SerializedName("image")
    val image: String?,
    /**
     * The wiki of the character
     */
    @SerializedName("wiki")
    val wiki: String?
)