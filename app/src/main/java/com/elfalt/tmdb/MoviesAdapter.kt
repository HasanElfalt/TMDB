package com.elfalt.tmdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.elfalt.tmdb.Ret.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_movies_content.view.*

class MoviesAdapter(val moviesList : List<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_movies_content,parent,false)
        return MovieViewHolder(movieView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.movieName.text = moviesList[position].original_title
        val releaseDate: ArrayList<String>  = moviesList[position].release_date.split('-') as ArrayList<String>
        holder.movieYear.text = releaseDate[0]

        Picasso.get().load(BASE_IMAGE_URL + moviesList[position].poster_path).into(holder.poster)
    }
    override fun getItemCount(): Int {
        return moviesList.size
    }

    class MovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val poster : ImageView = itemView.poster
        val movieName : TextView = itemView.movieName
        val movieYear : TextView = itemView.movieYear

    }

    companion object {
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w400"
    }
}