package fr.deuspheara.potterdbapp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import fr.deuspheara.potterdbapp.core.model.character.CharacterFullModel
import fr.deuspheara.potterdbapp.core.model.character.CharacterLightModel

@Entity(tableName = "characters")
data class CharacterEntity (
    /** The slug of the character */
    @PrimaryKey(autoGenerate = false)
    val slug: String,

    /** Info is favorite or not */
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean,

    /** Name of the character */
    @ColumnInfo(name = "name")
    val name: String,

    /** Image of the character */
    @ColumnInfo(name = "image")
    val image: String,

    /** Species of the character */
    @ColumnInfo(name = "species")
    val species: String,

    /** Gender of the character */
    @ColumnInfo(name = "gender")
    val gender: String,

    /** House of the character */
    @ColumnInfo(name = "house")
    val house: String,

    /** Born of the character */
    @ColumnInfo(name = "born")
    val born: String,

    /** Height of the character */
    @ColumnInfo(name = "height")
    val height: String,

    /** Weight of the character */
    @ColumnInfo(name = "weight")
    val weight: String,
){
    companion object {
        fun toCharacterLight(characterEntity: CharacterEntity): CharacterLightModel {
            return CharacterLightModel(
                slug = characterEntity.slug,
                name = characterEntity.name,
                image = characterEntity.image,
                species = characterEntity.species,
                gender = characterEntity.gender,
                house = characterEntity.house,
                born = characterEntity.born,
                height = characterEntity.height,
                weight = characterEntity.weight,
                isFavorite = characterEntity.isFavorite
            )
        }
    }
}
