package com.example.kotlindemo.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.R
import com.example.kotlindemo.loadUrl
import com.example.kotlindemo.model.User
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListAdapter(
    private val users: MutableList<User>,
    private val listener: (User, View) -> Unit) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) = holder.bind(users[position], listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_user, parent, false
            )
        )

    fun addUsers(newUsers: List<User>) {
        users.addAll(newUsers)
        notifyDataSetChanged()
    }

    fun clearUsers() {
        users.clear()
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("NewApi")
        fun bind(user: User, listener: (User, View) -> Unit) = with(itemView) {
            name.text = user.displayName
            reputation.text = "${user.reputation} points"
            user.profileImage?.let { userAvatar.loadUrl(it) }
            setOnClickListener { listener(user, userAvatar) }

            userAvatar.transitionName = "transition${user.userId}"
        }
    }

}