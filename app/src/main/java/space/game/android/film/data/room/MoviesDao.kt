package space.game.android.film.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import space.game.android.film.models.MovieItem

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movieItem: MovieItem)

    @Delete
    suspend fun delete(movieItem: MovieItem)

    @Query("SELECT * from movie_table")
    fun getAllMovies(): LiveData<List<MovieItem>>
}