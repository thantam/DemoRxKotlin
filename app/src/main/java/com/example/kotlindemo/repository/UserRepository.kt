package com.example.kotlindemo.repository

import android.util.Log
import com.example.kotlindemo.model.*
import com.example.kotlindemo.services.API
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers

class UserRepository() {

    fun getUsers(page: Int) = API.service.getUsers(page)

    fun getDetails(userId: Long) : Single<DetailsModel> {
        return Single.zip(
            API.service.getQuestionsByUser(userId),
                getAnswers(userId),
            API.service.getFavoritesByUser(userId),
                Function3<QuestionListModel, List<AnswerViewModel>, QuestionListModel, DetailsModel>
                { questions, answers, favorites ->
                    createDetailsModel(questions, answers, favorites) })
    }

    private fun getAnswers(userId: Long) : Single<List<AnswerViewModel>> {
        return API.service.getAnswersByUser(userId)
            .flatMap { answerListModel: AnswerListModel ->
                    Log.d("Ahihi", "map : run on ${Thread.currentThread().id} - - ${Thread.currentThread().name}")
                    mapAnswersToAnswerViewModels(answerListModel.items) }
    }

    private fun mapAnswersToAnswerViewModels(answers: List<Answer>): Single<List<AnswerViewModel>> {
        val processedAnswers = answers
                .filter { it.accepted }
                .take(3)

        val ids = processedAnswers
                .map { it.questionId.toString() }
                .joinToString(separator = ";")

        Log.d("Ahihi","ids: $ids")
        val questionsListModel = API.service.getQuestionById(ids)

        return questionsListModel
                .map { questionListModel: QuestionListModel? ->
                    addTitlesToAnswers(processedAnswers, questionListModel?.items ?: emptyList()) }
    }

    private fun addTitlesToAnswers(answers: List<Answer>, questions: List<Question>) : List<AnswerViewModel> {
        return answers.map { (answerId, questionId, score, accepted) ->
            val question = questions.find { it.questionId == questionId }
            AnswerViewModel(answerId, score, accepted, question?.title ?: "Unknown")
        }
    }

    private fun createDetailsModel(questionsModel: QuestionListModel, answersModel: List<AnswerViewModel>,
                                   favoritesModel: QuestionListModel): DetailsModel {
        val questions = questionsModel.items
                .take(3)

        val favorites = favoritesModel.items
                .take(3)

        return DetailsModel(questions, answersModel, favorites)
    }
}