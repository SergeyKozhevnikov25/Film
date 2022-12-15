package space.game.android.film.presentation.screens.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import space.game.android.film.REALISATION
import space.game.android.film.models.MovieItem

class FavoriteViewModel: ViewModel() {

    fun getAllMovies(): LiveData<List<MovieItem>> {
        return REALISATION.allMovies
    }
}
