package com.example.kotlindemo.view

import com.example.kotlindemo.model.DetailsModel
import com.example.kotlindemo.model.User

interface UserListView {
    fun showLoading()
    fun hideLoading()
    fun addUsersToList(users: List<User>)
    fun showError()
}