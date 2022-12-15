package space.game.android.film.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import space.game.android.film.REALISATION
import space.game.android.film.models.MovieItem

class DetailViewModel : ViewModel() {

    fun insert(movieItemDbModel: MovieItem, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REALISATION.insertMovie(movieItemDbModel) {
                onSuccess()
            }
        }

    fun delete(movieItemDbModel: MovieItem, onSuccess: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            REALISATION.deleteMovie(movieItemDbModel) {
                onSuccess()
            }
        }
}