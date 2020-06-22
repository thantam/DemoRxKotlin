package com.example.kotlindemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.R
import com.example.kotlindemo.model.AnswerViewModel
import kotlinx.android.synthetic.main.list_item_answer.view.*

class AnswerDelegateAdapter(private val listener: (String) -> Unit) : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup)
            = AnswerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_answer, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as AnswerViewHolder
        holder.bind(item as AnswerViewModel, listener)
    }

    class AnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(answer: AnswerViewModel, listener: (String) -> Unit) = with(itemView) {
            score.text = "${answer.score} points"
            questionTitle.text = answer.questionTitle

            setOnClickListener { listener("https://stackoverflow.com/a/${answer.answerId}") }
        }
    }
}