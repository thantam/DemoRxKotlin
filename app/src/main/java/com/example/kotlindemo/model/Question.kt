package com.example.kotlindemo.model

import com.example.kotlindemo.adapters.AdapterConstants
import com.example.kotlindemo.adapters.ViewType
import com.google.gson.annotations.SerializedName

data class Question(
        @SerializedName("view_count") val viewCount: Long,
        @SerializedName("score") val score: Long,
        @SerializedName("title") val title: String,
        @SerializedName("link") val link: String,
        @SerializedName("question_id") val questionId: Long,
        @SerializedName("creation_date") val creationDate: Long) : ViewType {

    override fun getViewType(): Int = AdapterConstants.QUESTION
}
