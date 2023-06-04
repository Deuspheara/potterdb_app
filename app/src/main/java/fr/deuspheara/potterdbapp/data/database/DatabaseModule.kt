package fr.deuspheara.potterdbapp.data.database

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import androidx.room.Room
import fr.deuspheara.potterdbapp.data.database.room.FavoriteDatabase

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideCagibiDatabase(
        @ApplicationContext context: Context
    ) : FavoriteDatabase {
        return Room.databaseBuilder(
            context,
            FavoriteDatabase::class.java,
            FavoriteDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}