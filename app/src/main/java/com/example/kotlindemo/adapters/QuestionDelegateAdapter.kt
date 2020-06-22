package com.example.kotlindemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.R
import com.example.kotlindemo.model.Question
import kotlinx.android.synthetic.main.list_item_question.view.*

class QuestionDelegateAdapter(private val listener: (String) -> Unit) : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup)
            = QuestionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_question, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as QuestionViewHolder
        holder.bind(item as Question, listener)
    }

    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(question: Question, listener: (String) -> Unit) = with(itemView) {
            title.text = question.title
            score.text = "${question.score} points"
            viewCount.text = "Viewed: ${question.viewCount}"

            setOnClickListener { listener(question.link) }
        }
    }
}