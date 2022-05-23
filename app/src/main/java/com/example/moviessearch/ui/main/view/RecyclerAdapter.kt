package com.example.moviessearch.ui.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviessearch.R
import com.example.moviessearch.ui.main.model.Movies

class RecyclerAdapter(private var source: MutableList<Movies>?) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_view, parent, false)
        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = source?.get(position)
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return source?.size!!
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textView: TextView? = itemView.findViewById(R.id.movies_name)
        private val imageView: ImageView = itemView.findViewById(R.id.movies_image)
        fun bind(movies: Movies?) {
            textView?.text = movies?.title

        }
    }
}