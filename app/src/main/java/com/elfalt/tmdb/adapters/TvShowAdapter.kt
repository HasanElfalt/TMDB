package com.elfalt.tmdb.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.elfalt.tmdb.R
import com.elfalt.tmdb.remoteSource.TvShow
import com.elfalt.tmdb.ui.TvDetailsActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_tv.view.*

class TvShowAdapter(private val tvList : List<TvShow>) :
    RecyclerView. Adapter<TvShowAdapter.TvShowViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val tvShowView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_tv, parent, false)
        return TvShowViewHolder(tvShowView)

    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {

        holder.tvShowName.text = tvList[position].original_name
        holder.tvId.text = tvList[position].id.toString()

        Picasso.get().load(BASE_IMAGE_URL + tvList[position].poster_path).into(holder.poster)

    }

    override fun getItemCount(): Int {
        return tvList.size
    }

    class TvShowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val poster: ImageView = itemView.tv_show_poster
        val tvShowName: TextView = itemView.tv_shw_title
        val tvId: TextView = itemView.tv_id


        init {
            itemView.setOnClickListener {

                val tvDetailsIntent = Intent(it.context, TvDetailsActivity::class.java)
                tvDetailsIntent.putExtra("id", tvId.text)
                startActivity(it.context, tvDetailsIntent, null)

            }
        }

    }
    companion object {
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w300"
    }
}
