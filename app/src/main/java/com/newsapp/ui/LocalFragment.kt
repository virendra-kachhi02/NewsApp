package com.newsapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshay.newsapp.core.utils.observeNotNull
import com.akshay.newsapp.core.utils.toast
import com.newsapp.adapters.NewsAdapter
import com.newsapp.data.NewsArticleDb
import com.newsapp.databinding.FragmentLocalBinding
import com.newsapp.di.ViewState
import com.newsapp.ui.viewmodels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class LocalFragment : Fragment() {
    private lateinit var _binding: FragmentLocalBinding
    private val viewViewModel: MovieViewModel by viewModels()
    lateinit var newsAdapter: NewsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLocalBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Setting up RecyclerView and adapter
        setRecycleView()
        // Update the UI on state change
        viewViewModel.getNewsArticles().observeNotNull(this) { state ->
            when (state) {
                is ViewState.Success -> {
                    _binding.progressCircular.visibility = View.GONE
                    _binding.recycler.visibility = View.VISIBLE
                    newsAdapter.postNews(state.data as ArrayList<NewsArticleDb>)
                }
                is ViewState.Loading -> {
                    _binding.progressCircular.visibility = View.VISIBLE
                    _binding.recycler.visibility = View.GONE
                }
                is ViewState.Error -> {
                    toast("Something went wrong ¯\\_(ツ)_/¯ => ${state.message}")
                }
            }
        }
    }

    private fun setRecycleView() {
        newsAdapter = NewsAdapter(ArrayList<NewsArticleDb>())
        _binding.recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
    }
}