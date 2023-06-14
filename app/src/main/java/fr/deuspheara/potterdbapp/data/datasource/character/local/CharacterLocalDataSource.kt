package fr.deuspheara.potterdbapp.data.datasource.character.local

import android.util.Log
import androidx.room.withTransaction
import fr.deuspheara.potterdbapp.core.coroutine.DispatcherModule
import fr.deuspheara.potterdbapp.data.database.model.CharacterEntity
import fr.deuspheara.potterdbapp.data.database.room.FavoriteDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CharacterLocalDataSource {
    /**
     * SQL query to insert a [CharacterEntity]
     */
    suspend fun insertCharacterIfNotExist(entity: CharacterEntity) : Long

    /**
     * SQL query to insert list of [CharacterEntity]
     */
    suspend fun insertAllCharacters(entities: List<CharacterEntity>) : List<Long>

    /**
     * SQL query to retrieve all [CharacterEntity] from the "characters" table
     */
    fun getAllCharacters(): Flow<List<CharacterEntity>>

    /**
     * SQL query to retrieve a [CharacterEntity] from the "characters" table by slug
     */
    suspend fun getCharacterBySlug(slug: String): CharacterEntity?

    /**
     * SQL query to update Favorite state
     */
    suspend fun toggleFavoriteStatus(slug: String, isFavorite: Boolean)
}

class CharacterLocalDataSourceImpl @Inject constructor(
    @DispatcherModule.DispatcherIO private val ioContext: CoroutineDispatcher,
    private val database: FavoriteDatabase
) : CharacterLocalDataSource {

    private companion object {
        private const val TAG = "CharacterLocalDataSourceImpl"
    }

    override suspend fun insertCharacterIfNotExist(entity: CharacterEntity) : Long = withContext(ioContext) {
            try {
                val existingCharacter = database.withTransaction {
                    database.characterDao.getCharacterBySlug(entity.slug)
                }

                if (existingCharacter == null) {
                    database.withTransaction {
                        database.characterDao.insertCharacter(entity)
                    }
                } else {
                    0 // if character already exists, return 0 to denote no new insertion
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error while inserting character in database with slug: ${entity.slug}, error: $e")
                throw e
            }

    }

    override suspend fun insertAllCharacters(entities: List<CharacterEntity>) : List<Long> = withContext(ioContext) {
        try {
            database.withTransaction {
                database.characterDao.insertAllCharacters(entities)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error while inserting characters, error: $e")
            throw e
        }
    }

    override fun getAllCharacters(): Flow<List<CharacterEntity>> = flow {
        try {
            emitAll(database.characterDao.getAllCharacters())
        } catch (e: Exception) {
            Log.e(TAG, "Error while getting characters, error: $e")
            throw e
        }
    }.flowOn(ioContext)

    override suspend fun getCharacterBySlug(slug: String): CharacterEntity? = withContext(ioContext) {
        try {
            database.characterDao.getCharacterBySlug(slug)
        } catch (e: Exception) {
            Log.e(TAG, "Error while getting character by slug, error: $e")
            throw e
        }
    }

    override suspend fun toggleFavoriteStatus(slug: String, isFavorite: Boolean) {
        withContext(ioContext){
            try {
                database.characterDao.updateFavoriteStatus(slug,isFavorite)
            } catch (e: Exception) {
                Log.e(TAG, "Error while updating favorite state of character with slug $slug, error: $e")
                throw e
            }
        }
    }
}
