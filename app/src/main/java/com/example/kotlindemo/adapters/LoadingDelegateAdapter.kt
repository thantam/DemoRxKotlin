package com.example.kotlindemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.R

class LoadingDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup)
            = LoadingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_loading, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}