package fr.deuspheara.potterdbapp.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharacterEntity (
    /** The slug of the character */
    @PrimaryKey(autoGenerate = false)
    val slug: String,

    /** Name of the character */
    @ColumnInfo(name = "name")
    val name: String,

    /** Description of the character */
    @ColumnInfo(name = "description")
    val description: String
)
