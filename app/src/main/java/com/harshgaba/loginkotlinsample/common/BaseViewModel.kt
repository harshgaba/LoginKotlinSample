package com.harshgaba.loginkotlinsample.common

import androidx.lifecycle.ViewModel
import com.harshgaba.loginkotlinsample.dagger.components.DaggerViewModelInjector
import com.harshgaba.loginkotlinsample.dagger.components.ViewModelInjector
import com.harshgaba.loginkotlinsample.dagger.modules.NetworkModule
import com.harshgaba.loginkotlinsample.ui.login.LoginViewModel
import com.harshgaba.loginkotlinsample.ui.users.UserSnippetViewModel
import com.harshgaba.loginkotlinsample.ui.users.UsersListViewModel

/**
 * Created by Harsh Gaba on 2019-06-12.
 * harshgaba08@gmail.com
 */


abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is UsersListViewModel -> injector.inject(this)
            is UserSnippetViewModel -> injector.inject(this)
            is LoginViewModel -> injector.inject(this)
        }
    }}