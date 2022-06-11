package com.example.moviessearch.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.moviessearch.R
import com.example.moviessearch.databinding.MainFragmentBinding
import com.example.moviessearch.ui.main.model.MovieReview
import com.example.moviessearch.ui.main.viewmodel.AppState
import com.example.moviessearch.ui.main.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModel()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private var adapter: RecyclerAdapter? = null


    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            binding.moviesRecyclerView.adapter = adapter

            val observer = Observer<AppState> { renderData(it) }
            viewModel.liveData.observe(viewLifecycleOwner, observer)
            viewModel.getMovie()
        }
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.Loading -> {
                viewProgressBar.visibility = View.VISIBLE
                viewGroup.visibility = View.GONE
            }
            is AppState.Success -> {
                viewProgressBar.visibility = View.GONE
                viewGroup.visibility = View.VISIBLE
                adapter = RecyclerAdapter(object : SetClick {
                    override fun onItemSetClick(movieReview: MovieReview) {
                        val manager = activity?.supportFragmentManager
                        manager.let { manager ->
                            val bundle = Bundle().apply {
                                putParcelable(DetailsFragment.BUNDLE_EXTRA, movieReview)
                            }
                            manager?.beginTransaction()
                                ?.add(R.id.container, DetailsFragment.newInstance(bundle))
                                ?.addToBackStack("")
                                ?.commitAllowingStateLoss()
                        }
                    }
                }).apply { setMoviesReview(appState.moviesData) }
            }

            is AppState.MyError -> {
                viewProgressBar.visibility = View.GONE
                viewGroup.visibility = View.GONE
                Snackbar.make(main, "Error", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Reset") { viewModel.getMovie() }
                    .show()
            }
        }
    }

//    private fun updateAdapter(){
//        adapter.notifyDataSetChanged()
//    }

    interface SetClick {
        fun onItemSetClick(movieReview: MovieReview)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}