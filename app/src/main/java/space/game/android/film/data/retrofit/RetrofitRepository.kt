package space.game.android.film.data.retrofit

import retrofit2.Response
import space.game.android.film.data.retrofit.api.RetrofitInstance
import space.game.android.film.models.MoviesModel

class RetrofitRepository {
    suspend fun getMovies(): Response<MoviesModel> {
        return RetrofitInstance.api.getPopularMovie()
    }
}