package fr.deuspheara.potterdbapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.deuspheara.potterdbapp.data.database.model.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    /**
     * SQL query to insert a [CharacterEntity]
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacter(entity: CharacterEntity) : Long

    /**
     * SQL query to insert list of [CharacterEntity]
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllCharacters(entities: List<CharacterEntity>) : List<Long>

    /**
     * SQL query to retrieve all [CharacterEntity] from the "characters" table
     */
    @Query("SELECT * FROM characters")
    fun getAllCharacters(): Flow<List<CharacterEntity>>

    /**
     * SQL query to retrieve a [CharacterEntity] from the "characters" table by slug
     */
    @Query("SELECT * FROM characters WHERE slug = :slug LIMIT 1")
    suspend fun getCharacterBySlug(slug: String): CharacterEntity?
}
