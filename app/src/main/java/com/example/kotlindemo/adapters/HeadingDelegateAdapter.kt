package com.example.kotlindemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.R
import com.example.kotlindemo.model.Heading
import kotlinx.android.synthetic.main.list_item_heading.view.*

class HeadingDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup)
            = HeadingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_heading, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as HeadingViewHolder
        holder.bind(item as Heading)
    }

    class HeadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(heading: Heading) = with(itemView) {
            title.text = heading.title
        }
    }
}