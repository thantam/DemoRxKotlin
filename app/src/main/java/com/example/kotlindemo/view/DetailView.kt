package com.example.kotlindemo.view

import com.example.kotlindemo.model.DetailsModel

interface DetailView {
    fun showDetails(detailsModel: DetailsModel)
    fun showError()
    fun showLoading()
    fun hideLoading()
}