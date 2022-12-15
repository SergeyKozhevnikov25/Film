package space.game.android.film.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import space.game.android.film.models.MovieItem


@Database(entities = [MovieItem::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MoviesDao

    companion object {
        private var database: MoviesDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

//        fun getInstance(context: Context): MoviesDatabase {
//            synchronized(LOCK) {
//                database?.let { return it }
//                val instance = Room
//                    .databaseBuilder(context, MoviesDatabase::class.java, "db").build()
//                database = instance
//                return instance
//            }
//        }

        @Synchronized
        fun getInstance(context: Context): MoviesDatabase {
            return if (database == null) {
                database = Room
                    .databaseBuilder(context, MoviesDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                database as MoviesDatabase
            } else {
                database as MoviesDatabase
            }
        }
    }
}
