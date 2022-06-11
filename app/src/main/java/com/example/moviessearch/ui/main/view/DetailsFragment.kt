package com.example.moviessearch.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.moviessearch.databinding.FragmentDetailsBinding
import com.example.moviessearch.ui.main.model.MovieReview
import com.example.moviessearch.ui.main.model.Movies


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<MovieReview>(BUNDLE_EXTRA).let {
            if (it != null) {
                renderData(it)
            }
        }
    }

    private fun renderData(movieReview: MovieReview) {
        with(binding){
            val movie = movieReview.title
            dfMovieName.text =movie.title
            dfMovieDirector.text = "Joss Whedon"
        }

    }


    companion object {
        const val BUNDLE_EXTRA = "movie"
        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}