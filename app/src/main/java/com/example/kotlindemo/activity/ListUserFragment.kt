package com.example.kotlindemo.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.R
import com.example.kotlindemo.adapters.UserListAdapter
import com.example.kotlindemo.model.User
import com.example.kotlindemo.presentation.UserListPresenter
import com.example.kotlindemo.repository.UserRepository
import com.example.kotlindemo.view.UserListView
import kotlinx.android.synthetic.main.layout_list_fragment.*

class ListUserFragment : Fragment(), UserListView {
    private lateinit var presenter: UserListPresenter
    private val adapterList by lazy {
        val userList = mutableListOf<User>()
        UserListAdapter(userList) { user, view ->
            openDetailFragment(user, view)
        }
    }

    private fun openDetailFragment(user: User, view: View) {
        val detailsFragment = DetailsFragment.newInstance(user)
        (activity as MainActivity).addDetailsFragmentWithTransition(detailsFragment, view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = UserListPresenter(UserRepository())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.layout_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initAdapter()
        presenter.attachView(this)

        if (adapterList.itemCount == 0) {
            showLoading()
            presenter.getUsers()
        }
    }

    private fun initViews() {
        swipeRefreshLayout.setOnRefreshListener {
            adapterList.clearUsers()
            presenter.resetPaging()
            presenter.getUsers()
        }
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun addUsersToList(users: List<User>) {
        adapterList.addUsers(users)
    }

    override fun showError() {
        Toast.makeText(activity, "Couldn't load data", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    private fun initAdapter() {
        val linearLayoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        recyclerView.apply {
            adapter = adapterList
            layoutManager = linearLayoutManager
        }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val lastVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition() + linearLayoutManager.childCount
                val totalItemCount = linearLayoutManager.itemCount

                presenter.onScrollChanged(lastVisibleItemPosition, totalItemCount)
            }
        })
    }
}