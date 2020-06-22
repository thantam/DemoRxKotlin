package com.example.kotlindemo.model

import com.example.kotlindemo.adapters.AdapterConstants
import com.example.kotlindemo.adapters.ViewType


data class AnswerViewModel(
        val answerId: Long,
        val score: Long,
        val accepted: Boolean,
        val questionTitle: String) : ViewType {

    override fun getViewType() = AdapterConstants.ANSWER
}