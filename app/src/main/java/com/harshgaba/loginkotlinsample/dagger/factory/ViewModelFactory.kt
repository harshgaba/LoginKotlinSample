package com.harshgaba.loginkotlinsample.dagger.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import android.content.Context
import com.harshgaba.loginkotlinsample.database.CredentialsDatabase
import com.harshgaba.loginkotlinsample.ui.login.LoginViewModel
import com.harshgaba.loginkotlinsample.ui.users.UsersListViewModel

class ViewModelFactory(private val context: Context): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersListViewModel::class.java)) {
            return UsersListViewModel() as T
        }else if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            val db = CredentialsDatabase.getInstance(context)
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(db.credentialsDAO()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}