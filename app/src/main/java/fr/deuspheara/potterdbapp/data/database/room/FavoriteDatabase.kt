package fr.deuspheara.potterdbapp.data.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.deuspheara.potterdbapp.data.database.dao.CharacterDao
import fr.deuspheara.potterdbapp.data.database.model.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = true
)
abstract class FavoriteDatabase : RoomDatabase() {
    internal companion object {
        const val DATABASE_NAME = "favorites.db"
    }

    abstract val characterDao : CharacterDao
}