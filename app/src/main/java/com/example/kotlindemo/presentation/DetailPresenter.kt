package com.example.kotlindemo.presentation

import android.annotation.SuppressLint
import android.util.Log
import com.example.kotlindemo.repository.UserRepository
import com.example.kotlindemo.view.DetailView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailPresenter(private val userRepository: UserRepository) : BasePresenter<DetailView>() {

    @SuppressLint("CheckResult")
    fun getDetails(id: Long) {
        view?.showLoading()
        val startTime = System.nanoTime()
        userRepository.getDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                    detailsModel ->
                    view?.hideLoading()
                    view?.showDetails(detailsModel)
                    val endTime   = System.nanoTime()
                    Log.d("Monica", "time perform: ${endTime - startTime}")
                },
                {
                    error ->
                    view?.hideLoading()
                    view?.showError()
                })
    }
}
