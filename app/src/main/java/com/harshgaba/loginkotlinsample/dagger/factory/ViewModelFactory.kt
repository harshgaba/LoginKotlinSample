package com.harshgaba.loginkotlinsample.dagger.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import com.harshgaba.loginkotlinsample.ui.users.UsersListViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersListViewModel::class.java)) {
            return UsersListViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}