package com.gaspardeelias.rssreaderdemo.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gaspardeelias.rssreaderdemo.R
import com.gaspardeelias.rssreaderdemo.repository.model.Feed

class FeedListAdapter(val context: Context) : RecyclerView.Adapter<FeedVH>() {

    var list = listOf<Feed>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FeedVH(LayoutInflater.from(context).inflate(R.layout.feed_list_item, null))


    override fun onBindViewHolder(holder: FeedVH, position: Int) {
        holder.bind(list.get(position))
    }

    override fun getItemCount() = list.size
}


class FeedVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(feed: Feed) {
        itemView.findViewById<TextView>(R.id.feed_id).text = feed.id.toString()
        itemView.findViewById<TextView>(R.id.feed_url).text = feed.url.toString()
    }
}