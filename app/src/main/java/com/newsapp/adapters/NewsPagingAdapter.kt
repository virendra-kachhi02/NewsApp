package com.newsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.newsapp.BR
import com.newsapp.data.NewsArticleDb
import com.newsapp.databinding.NewsRowBinding

class NewsPagingAdapter() :
    ListAdapter<NewsArticleDb, NewsPagingAdapter.MyViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsArticleDb>() {
            override fun areItemsTheSame(oldItem: NewsArticleDb, newItem: NewsArticleDb): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: NewsArticleDb,
                newItem: NewsArticleDb
            ): Boolean = oldItem == newItem
        }
    }

    inner class MyViewHolder(val viewDataBinding: NewsRowBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewDataBinding.setVariable(BR.news, getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            NewsRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return MyViewHolder(binding)
    }
}