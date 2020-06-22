package com.example.kotlindemo.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.R
import com.example.kotlindemo.loadUrl
import com.example.kotlindemo.model.User
import kotlinx.android.synthetic.main.list_item_user_detail.view.*

class UserDetailsDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup)
            = UserDetailsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_user_detail, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as UserDetailsViewHolder
        holder.bind(item as User)
    }

    class UserDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("NewApi")
        fun bind(user: User) = with(itemView) {
            user.profileImage?.let { profileImage.loadUrl(it) }
            name.text = user.displayName
            reputation.text = "${user.reputation} points"

            profileImage.transitionName = "transition${user.userId}"
        }
    }
}