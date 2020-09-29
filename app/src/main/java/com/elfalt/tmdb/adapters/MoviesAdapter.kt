package com.elfalt.tmdb.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.elfalt.tmdb.R
import com.elfalt.tmdb.ui.MovieDetailsActivity
import com.elfalt.tmdb.ui.Movies
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_movies_content.view.*

class MoviesAdapter(val moviesList : List<Movies>) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movieView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_movies_content,parent,false)
        return MovieViewHolder(movieView)

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.movieName.text = moviesList[position].original_title
        val releaseDate: ArrayList<String>  = moviesList[position].release_date.split('-') as ArrayList<String>
        holder.movieYear.text = releaseDate[0]
        holder.id.text = moviesList[position].id.toString()
        holder.ratingBar.rating = moviesList[position].vote_average / 2

        Picasso.get().load(BASE_IMAGE_URL + moviesList[position].poster_path).into(holder.poster)

    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    inner class MovieViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val poster : ImageView = itemView.poster
        val movieName : TextView = itemView.movieName
        val movieYear : TextView = itemView.movieYear
        val id : TextView = itemView.movieId
        val ratingBar : RatingBar = itemView.rating_bar

        init {
            itemView.setOnClickListener{

                val movieDetailsIntent = Intent(it.context, MovieDetailsActivity:: class.java)
                movieDetailsIntent.putExtra("id",itemView.movieId.text)
                startActivity(it.context,movieDetailsIntent,null)

            }
        }

    }

    companion object {
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w300"
    }
}