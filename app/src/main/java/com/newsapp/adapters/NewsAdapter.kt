package com.newsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.newsapp.R
import com.newsapp.data.NewsArticleDb
import com.newsapp.databinding.NewsRowBinding


class NewsAdapter(private val newsData: ArrayList<NewsArticleDb>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private var newses: ArrayList<NewsArticleDb> = newsData

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(val binding: NewsRowBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            NewsRowBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup, false
            )
        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        with(viewHolder) {
            binding.news = newses[position];
            binding.tvNewsDesc.text = newses[position].description
            binding.tvNewsType.text = newses[position].publishedAt
            binding.tvAuthor.text = newses[position].author
            Glide
                .with(viewHolder.itemView.context)
                .load(newses[position].urlToImage)
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.ivNewsImage)
        }
        viewHolder.itemView.setOnClickListener {

        }

    }

    fun postNews(newData: ArrayList<NewsArticleDb>) {
        newses = newData
        notifyDataSetChanged()
    }

    // Return the size of your newses (invoked by the layout manager)
    override fun getItemCount() = newses.size

}

