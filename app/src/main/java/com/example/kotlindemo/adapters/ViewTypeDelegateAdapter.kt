package com.example.kotlindemo.adapters
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Tamas_Kozmer on 7/6/2017.
 */
interface ViewTypeDelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}
