package me.hafizdwp.jetpack_submission_final.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.hafizdwp.jetpack_submission_final.data.model.Movreak
import me.hafizdwp.jetpack_submission_final.data.source.local.dao.FavoriteDao
import me.hafizdwp.jetpack_submission_final.utils.RoomConverters

@Database(entities = [Movreak::class],
        version = 1,
        exportSchema = false)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        /** The only instance  */
        private var instance: AppDatabase? = null

        /**
         * Gets the singleton instance of AppDatabase.
         *
         * @param context The context.
         * @return The singleton instance of AppDatabase.
         */
        @Synchronized
        fun getInstance(context: Context?): AppDatabase? {
            if (instance == null && context != null) {
                instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "movreak.db"
                )
                        .fallbackToDestructiveMigration()
                        .build()
            }

            return instance
        }
    }

    abstract fun favoriteDao(): FavoriteDao
}