package space.game.android.film.presentation.screens.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import space.game.android.film.REALISATION
import space.game.android.film.data.retrofit.RetrofitRepository
import space.game.android.film.data.room.MoviesDatabase
import space.game.android.film.data.room.repository.MoviesRepositoryRealization
import space.game.android.film.models.MoviesModel

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RetrofitRepository()
    val myMovies: MutableLiveData<Response<MoviesModel>> = MutableLiveData()
    val context = application


    fun getMoviesRetrofit() {
        viewModelScope.launch {
            try {
                myMovies.value = repository.getMovies()
                Log.e("ERROR", myMovies.toString())
            } catch (e: Exception) {
                Log.e("ERROR", e.message.toString())
            }
        }
    }

        fun initDatabase() {
            val daoMovie = MoviesDatabase.getInstance(context).getMovieDao()
            REALISATION = MoviesRepositoryRealization(daoMovie)
        }

    }