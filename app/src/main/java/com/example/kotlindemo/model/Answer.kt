package com.example.kotlindemo.model

import com.example.kotlindemo.adapters.AdapterConstants
import com.example.kotlindemo.adapters.ViewType
import com.google.gson.annotations.SerializedName
class Answer(
    @SerializedName("answer_id") val answerId: Long,
    @SerializedName("question_id") val questionId: Long,
    @SerializedName("score") val score: Long,
    @SerializedName("is_accepted") val accepted: Boolean) : ViewType {

    override fun getViewType(): Int = AdapterConstants.ANSWER
    operator fun component1(): Long {
        return answerId
    }operator fun component2(): Long {
        return questionId
    }operator fun component3(): Long {
        return score
    }operator fun component4(): Boolean {
        return accepted
    }

}