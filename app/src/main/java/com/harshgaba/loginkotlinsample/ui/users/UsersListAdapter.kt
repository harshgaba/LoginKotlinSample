package com.harshgaba.loginkotlinsample.ui.users

import android.databinding.DataBindingUtil
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.harshgaba.loginkotlinsample.R
import com.harshgaba.loginkotlinsample.databinding.UserSnippetBinding
import com.harshgaba.loginkotlinsample.models.User
import com.harshgaba.loginkotlinsample.ui.views.CollapsibleCardView

/**
 * Created by Harsh Gaba on 2019-06-12.
 * harshgaba08@gmail.com
 */

class UsersListAdapter : RecyclerView.Adapter<UsersListAdapter.ViewHolder>(), ViewUpdationHandler {

    private lateinit var usersList: List<User>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListAdapter.ViewHolder {
        val binding: UserSnippetBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.user_snippet, parent, false)
        binding.handlers = this
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersListAdapter.ViewHolder, position: Int) {
        holder.bind(usersList[position])
    }

    override fun getItemCount(): Int {
        return if (::usersList.isInitialized) usersList.size else 0
    }

    fun updateUsersList(postList: List<User>) {
        this.usersList = postList
        notifyDataSetChanged()
    }

    override fun expandCardView(view: View) {
        if (null != view) {
            val cardView=view as CardView
            val c=CollapsibleCardView(cardView)
            c.toggle()
        }
    }

    class ViewHolder(private val binding: UserSnippetBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = UserSnippetViewModel()

        fun bind(users: User) {
            viewModel.bind(users)
            binding.viewModel = viewModel
        }
    }
}