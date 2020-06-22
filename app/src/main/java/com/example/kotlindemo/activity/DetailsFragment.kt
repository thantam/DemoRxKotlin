package com.example.kotlindemo.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlindemo.R
import com.example.kotlindemo.adapters.DetailsAdapter
import com.example.kotlindemo.model.DetailsModel
import com.example.kotlindemo.model.User
import com.example.kotlindemo.presentation.DetailPresenter
import com.example.kotlindemo.repository.UserRepository
import com.example.kotlindemo.view.DetailView
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : Fragment(), DetailView {

    private lateinit var presenter: DetailPresenter
    private val detailsAdapter by lazy {
        DetailsAdapter { link ->
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            startActivity(browserIntent)
        }
    }

    companion object {
        fun newInstance(user: User): DetailsFragment {
            val fragment = DetailsFragment()
            val args = Bundle()
            args.putParcelable("user", user)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = DetailPresenter(UserRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater?.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)

        initAdapter()
        processArguments()
    }

    private fun processArguments() {
        val user = arguments?.getParcelable<User>("user")
        user?.let {
            Log.d("Monica","user id: " + user.userId)
            detailsAdapter.addItem(it)
            detailsAdapter.notifyDataSetChanged()
            presenter.getDetails(user.userId)
        }
    }

    private fun initAdapter() {
        detailsRecyclerView.layoutManager = LinearLayoutManager(activity)
        detailsRecyclerView.adapter = detailsAdapter
    }

    override fun showDetails(detailsModel: DetailsModel) {
        with(detailsAdapter) {
            addItemsWithHeading(detailsModel.questions, "Top questions by user")
            addItemsWithHeading(detailsModel.answers, "Top answers by user")
            addItemsWithHeading(detailsModel.favorites, "Favorited by user")
//            if (transitionEnded) {
                notifyDataSetChanged()
//            }
        }
    }

    override fun showError() {
        Toast.makeText(activity, "Couldn't load data", Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        detailsAdapter.addLoadingItem()
    }

    override fun hideLoading() {
        detailsAdapter.removeLoadingItem()
    }
}