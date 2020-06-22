package com.example.kotlindemo.presentation

import android.annotation.SuppressLint
import com.example.kotlindemo.model.User
import com.example.kotlindemo.model.UserListModel
import com.example.kotlindemo.view.UserListView
import com.example.kotlindemo.repository.UserRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserListPresenter(private val userRepository: UserRepository) : BasePresenter<UserListView>() {

    val offset = 5

    var page = 1
    var loading = false

    lateinit var mDisposable : Disposable

    @SuppressLint("CheckResult")
    fun getUsers() {
        loading = true
        userRepository.getUsers(page)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ userListModel ->
                loading = false
                view?.addUsersToList(userListModel.items)
                view?.hideLoading()
                page++
            },
                {
                    loading = false
                    view?.showError()
                    view?.hideLoading()
                })
    }

    fun resetPaging() {
        page = 1
    }

    fun getDataFromCache() : Single<UserListModel>{
        return Single.create<UserListModel> {
            it.onSuccess(UserListModel(listOf(User(1111, "name1", 54357, "https://www.gravatar.com/avatar/a92525f049e20db8f4ce3d62f3f0bb2b?s=128&d=identicon&r=PG"),
                User(2222, "name2", 543857, "https://www.gravatar.com/avatar/a92525f049e20db8f4ce3d62f3f0bb2b?s=128&d=identicon&r=PG"),
                User(3333, "name3", 543757, "https://www.gravatar.com/avatar/a92525f049e20db8f4ce3d62f3f0bb2b?s=128&d=identicon&r=PG"))))
        }
    }

    fun onScrollChanged(lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (!loading) {
            if (lastVisibleItemPosition >= totalItemCount - offset) {
                getUsers()
            }
        }

        if (loading && lastVisibleItemPosition >= totalItemCount) {
            view?.showLoading()
        }
    }
}