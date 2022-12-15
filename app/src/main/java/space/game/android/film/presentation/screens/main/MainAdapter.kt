package space.game.android.film.presentation.screens.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import space.game.android.film.MAIN
import space.game.android.film.R
import space.game.android.film.databinding.ItemLayoutBinding
import space.game.android.film.models.MovieItem

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var listMovies = emptyList<MovieItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<MovieItem>) {
        listMovies = list
        notifyDataSetChanged()
    }

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemLayoutBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val list = listMovies[position]
        holder.binding.itemTitle.text = list.title
        holder.binding.itemDate.text = list.release_date
        Glide.with(MAIN)
            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${list.poster_path}")
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.binding.itemImg)
    }

    override fun getItemCount(): Int = listMovies.size

    //обработка нажатий
    override fun onViewAttachedToWindow(holder: MainViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            MainFragment.clickMovie(listMovies[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MainViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.setOnClickListener(null)
    }
}
