package com.example.moviessearch.ui.main.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviessearch.R
import com.example.moviessearch.databinding.CardViewBinding
import com.example.moviessearch.databinding.MainFragmentBinding
import com.example.moviessearch.ui.main.model.MovieReview

class RecyclerAdapter(private val itemClickListener: MainFragment.SetClick) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {
    private var moviesData: List<MovieReview> = listOf()
    private lateinit var binding: CardViewBinding

    @SuppressLint("NotifyDataSetChanged")
    fun setMoviesReview(data: List<MovieReview>) {
        moviesData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.card_view, parent, false)
        binding = CardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding.root)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(moviesData[position])
    }

    override fun getItemCount() = moviesData.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var textView: TextView = itemView.findViewById(R.id.movies_name)
        private var poster: ImageView = itemView.findViewById(R.id.dp_movie_poster)
        fun bind(movieReview: MovieReview) = with(binding){
            moviesName.text = movieReview.title.title
//            textView.text = movieReview.title.title
//            poster.drawable

            root.setOnClickListener { itemClickListener.onItemSetClick(movieReview) }
        }
    }
}