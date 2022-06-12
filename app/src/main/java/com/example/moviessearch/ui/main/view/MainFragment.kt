package com.example.moviessearch.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.moviessearch.databinding.MainFragmentBinding
import com.example.moviessearch.ui.main.viewmodel.AppState
import com.example.moviessearch.ui.main.viewmodel.MainViewModel
import com.example.moviessearch.ui.main.model.Movies
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModel()
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: RecyclerAdapter
    private lateinit var listMovies: MutableList<Movies>

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        listMovies = mutableListOf()

        adapter = RecyclerAdapter(listMovies)
        binding.moviesRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val observer = Observer<AppState> { renderData(it) }
        viewModel.liveData.observe(viewLifecycleOwner, observer)
        viewModel.getMovie()

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
                listMovies.add(Movies())
                updateAdapter()
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

    private fun updateAdapter(){
        adapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}