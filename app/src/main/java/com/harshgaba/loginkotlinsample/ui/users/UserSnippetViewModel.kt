package com.harshgaba.loginkotlinsample.ui.users

import android.arch.lifecycle.MutableLiveData
import com.harshgaba.loginkotlinsample.common.BaseViewModel
import com.harshgaba.loginkotlinsample.models.User


/**
 * Created by Harsh Gaba on 2019-06-12.
 * harshgaba08@gmail.com
 */

class UserSnippetViewModel : BaseViewModel() {
    private val userFullName = MutableLiveData<String>()
    private val userAddress = MutableLiveData<String>()

    fun bind(user: User) {
        userFullName.value = user.name
        userAddress.value = user.address?.suite + ", " + user.address?.city
    }

    fun getUserFullName(): MutableLiveData<String> {
        return userFullName
    }

    fun getUserAddress(): MutableLiveData<String> {
        return userAddress
    }

}