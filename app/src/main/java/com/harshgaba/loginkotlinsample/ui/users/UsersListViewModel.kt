package com.harshgaba.loginkotlinsample.ui.users

import androidx.lifecycle.MutableLiveData
import android.view.View
import com.harshgaba.loginkotlinsample.R
import com.harshgaba.loginkotlinsample.common.BaseViewModel
import com.harshgaba.loginkotlinsample.models.User
import com.harshgaba.loginkotlinsample.network.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Harsh Gaba on 2019-06-12.
 * harshgaba08@gmail.com
 */
class UsersListViewModel : BaseViewModel() {
    @Inject
    lateinit var apiClient: ApiClient
    val usersListAdapter: UsersListAdapter = UsersListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadUsers() }

    private lateinit var usersListSubscription: Disposable

    init {
        loadUsers()
    }

    override fun onCleared() {
        super.onCleared()
        usersListSubscription.dispose()
    }

    private fun loadUsers() {
        usersListSubscription = apiClient.getUsersList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveUsersListStart() }
            .doOnTerminate { onRetrieveUsersListFinish() }
            .subscribe(
                { result -> onRetrieveUsersListSuccess(result) },
                { onRetrieveUsersListError() }
            )
    }

    private fun onRetrieveUsersListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveUsersListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveUsersListSuccess(postList: List<User>) {
        usersListAdapter.updateUsersList(postList)
    }

    private fun onRetrieveUsersListError() {
        errorMessage.value = R.string.error_users_list_api_failed
    }
}