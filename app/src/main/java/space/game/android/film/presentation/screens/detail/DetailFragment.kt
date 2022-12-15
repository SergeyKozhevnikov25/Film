package space.game.android.film.presentation.screens.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import space.game.android.film.MAIN
import space.game.android.film.R
import space.game.android.film.SaveShared
import space.game.android.film.databinding.FragmentDetailBinding
import space.game.android.film.models.MovieItem


class DetailFragment : Fragment() {

    private var dBinding: FragmentDetailBinding? = null
    private val binding get() = dBinding!!

    private lateinit var currentMovie: MovieItem
    private var isFavorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dBinding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        currentMovie = arguments?.getSerializable("movie") as MovieItem
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        val valueBool = SaveShared.getFavorite(MAIN, currentMovie.id.toString())
        if (isFavorite != valueBool) {
            binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }

        Glide.with(MAIN)
            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${currentMovie.poster_path}")
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.imgDetail)
        binding.tvDate.text = currentMovie.release_date
        binding.tvTitle.text = currentMovie.title
        binding.tvDescription.text = currentMovie.overview

        //слушатель на кнопку
        binding.imgDetailFavorite.setOnClickListener {
            isFavorite = if (!isFavorite) {
                binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                SaveShared.setFavorite(MAIN, currentMovie.id.toString(), true)
                viewModel.insert(currentMovie) {}
                true
            } else {
                binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                viewModel.delete(currentMovie) {}
                SaveShared.setFavorite(MAIN, currentMovie.id.toString(), false)
                false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dBinding = null
    }
}