package com.harshgaba.loginkotlinsample.ui.users

import android.arch.lifecycle.MutableLiveData
import android.text.TextUtils
import android.util.Log
import com.harshgaba.loginkotlinsample.common.BaseViewModel
import com.harshgaba.loginkotlinsample.models.Geo
import com.harshgaba.loginkotlinsample.models.User


/**
 * Created by Harsh Gaba on 2019-06-12.
 * harshgaba08@gmail.com
 */

class UserSnippetViewModel : BaseViewModel() {
    private val userFullName = MutableLiveData<String>()
    private val userAddress = MutableLiveData<String>()
    private val userContact = MutableLiveData<String>()
    private val userCoordinates = MutableLiveData<Geo>()
    private val user = MutableLiveData<User>()

    fun bind(user: User) {
        this.user.value=user
        userFullName.value = user.name
        userAddress.value = user.address?.suite + ", " + user.address?.street +"\n"+user.address?.city
        userContact.value=user.phone+"\n"+user.email
        userCoordinates.value=user.address?.geo
    }

    fun getUserFullName(): MutableLiveData<String> {
        return userFullName
    }

    fun getUserAddress(): MutableLiveData<String> {
        return userAddress
    }

    fun getUserContact(): MutableLiveData<String> {
        return userContact
    }

    fun getuserCoordinates(): MutableLiveData<Geo> {
        return userCoordinates
    }

    fun getuser(): MutableLiveData<User> {
        return user
    }

    fun redirectToMap(geo: Geo) {
        TODO("need to redirect to map screen")

        if (null != geo && !TextUtils.isEmpty(geo.lat) && !TextUtils.isEmpty(geo.lng)) {
            Log.i("User", " Coordinates "+geo.lat+" "+geo.lng)
        }else{
            Log.e("Error", "User Coordinates null");

        }
    }
    fun showDetails(user:User){
        if (null!=user){
            TODO("need to redirect to user details screen")
        }else
        {
            Log.e("Error: ", "Something went wrong!, User details empty")
        }
    }
}