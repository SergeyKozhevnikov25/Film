package space.game.android.film.data.room.repository

import androidx.lifecycle.LiveData
import space.game.android.film.models.MovieItem

interface MoviesRepository {
    val allMovies: LiveData<List<MovieItem>>
    suspend fun insertMovie(movieItemModel: MovieItem, onSuccess:() -> Unit)
    suspend fun deleteMovie(movieItemModel: MovieItem, onSuccess:() -> Unit)

}