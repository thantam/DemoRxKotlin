package com.example.kotlindemo.model

data class DetailsModel(
        val questions: List<Question>,
        val answers: List<AnswerViewModel>,
        val favorites: List<Question>)