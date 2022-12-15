package space.game.android.film.presentation.screens.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import space.game.android.film.MAIN
import space.game.android.film.R
import space.game.android.film.databinding.FragmentFavoriteBinding
import space.game.android.film.models.MovieItem


class FavoriteFragment : Fragment() {

    private var fBinding: FragmentFavoriteBinding? = null
    private val binding get() = fBinding!!

    lateinit var recyclerView: RecyclerView
    private val adapter by lazy { FavoriteAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
        recyclerView = binding.rvFavorite
        recyclerView.adapter = adapter
        viewModel.getAllMovies().observe(viewLifecycleOwner) {
            adapter.setList(it.asReversed())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fBinding = null
    }


    companion object{
        fun clickMovie(model: MovieItem){
            val bundle = Bundle()
            bundle.putSerializable("movie", model)
            MAIN.navController.navigate(R.id.action_favoriteFragment_to_detailFragment, bundle)
        }
    }
}