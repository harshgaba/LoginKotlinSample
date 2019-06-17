package com.harshgaba.loginkotlinsample.ui.users

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.harshgaba.loginkotlinsample.R
import com.harshgaba.loginkotlinsample.databinding.UserSnippetBinding
import com.harshgaba.loginkotlinsample.models.User

/**
 * Created by Harsh Gaba on 2019-06-12.
 * harshgaba08@gmail.com
 */

class UsersListAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {

    private lateinit var usersList: List<User>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListAdapter.ViewHolder {
        val binding: UserSnippetBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.user_snippet, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersListAdapter.ViewHolder, position: Int) {
        holder.bind(usersList[position])
    }

    override fun getItemCount(): Int {
        return if (::usersList.isInitialized) usersList.size else 0
    }

    fun updateUsersList(usersList: List<User>) {
        this.usersList = usersList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: UserSnippetBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root) {
        private val viewModel = UserSnippetViewModel()

        fun bind(users: User) {
            viewModel.bind(users)
            binding.viewModel = viewModel
        }
    }
}