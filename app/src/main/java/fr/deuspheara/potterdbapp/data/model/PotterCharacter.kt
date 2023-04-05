package fr.deuspheara.potterdbapp.data.model

import com.squareup.moshi.Json

data class PotterCharacter(
    @Json(name = "slug")
    val slug: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "born")
    val born: String,
    @Json(name = "died")
    val died: String,
    @Json(name = "gender")
    val gender: String,
    @Json(name = "species")
    val species: String,
    @Json(name = "height")
    val height: String,
    @Json(name = "weight")
    val weight: String,
    @Json(name = "hair_color")
    val hairColor: String,
    @Json(name = "eye_color")
    val eyeColor: String,
    @Json(name = "skin_color")
    val skinColor: String,
    @Json(name = "blood_status")
    val bloodStatus: String,
    @Json(name = "nationality")
    val nationality: String,
    @Json(name = "animagus")
    val animagus: String?,
    @Json(name = "boggart")
    val boggart: String?,
    @Json(name = "house")
    val house: String?,
    @Json(name = "patronus")
    val patronus: String?,
    @Json(name = "alias_names")
    val aliasNames: List<String>,
    @Json(name = "family_member")
    val familyMembers: List<String>,
    @Json(name = "jobs")
    val jobs: List<String>,
    @Json(name = "romances")
    val romances: List<String>,
    @Json(name = "titles")
    val titles: List<String>,
    @Json(name = "wands")
    val wands: List<String>,
    @Json(name = "image")
    val image: String,
    @Json(name = "wiki")
    val wiki: String
)
