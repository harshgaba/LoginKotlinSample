package com.harshgaba.loginkotlinsample.dagger.components

import com.harshgaba.loginkotlinsample.dagger.modules.NetworkModule
import com.harshgaba.loginkotlinsample.ui.users.UserSnippetViewModel
import com.harshgaba.loginkotlinsample.ui.users.UsersListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param usersListViewModel UsersListViewModel in which to inject the dependencies
     */
    fun inject(usersListViewModel: UsersListViewModel)
    /**
     * Injects required dependencies into the specified PostViewModel.
     * @param userSnippetViewModel UserSnippetViewModel in which to inject the dependencies
     */
    fun inject(userSnippetViewModel: UserSnippetViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}