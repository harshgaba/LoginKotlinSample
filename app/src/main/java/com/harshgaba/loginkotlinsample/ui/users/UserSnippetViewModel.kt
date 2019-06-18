package com.harshgaba.loginkotlinsample.ui.users

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.harshgaba.loginkotlinsample.common.BaseViewModel
import com.harshgaba.loginkotlinsample.models.User
import com.harshgaba.loginkotlinsample.ui.map.MapsActivity
import com.harshgaba.loginkotlinsample.utils.LAT
import com.harshgaba.loginkotlinsample.utils.LNG


/**
 * Created by Harsh Gaba on 2019-06-12.
 * harshgaba08@gmail.com
 */

class UserSnippetViewModel : BaseViewModel() {
    private val userFullName = MutableLiveData<String>()
    private val userAddress = MutableLiveData<String>()
    private val userContact = MutableLiveData<String>()
    private val userLat = MutableLiveData<String>()
    private val userLang = MutableLiveData<String>()
    private val userCompany = MutableLiveData<String>()
    private val userWebsite = MutableLiveData<String>()
    private val user = MutableLiveData<User>()

    fun bind(user: User) {
        this.user.value = user
        userFullName.value = user.name
        userAddress.value = user.address?.suite + ", " + user.address?.street + "\n" + user.address?.city
        userContact.value = user.phone + "\n" + user.email
        userLat.value = user.address?.geo?.lat
        userLang.value = user.address?.geo?.lng
        userWebsite.value = user.website
        userCompany.value = user.company?.name + "\n" + user.company?.bs + "\n" + user.company?.catchPhrase

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

    fun getUserLat(): MutableLiveData<String> {
        return userLat
    }

    fun getUserCompany(): MutableLiveData<String> {
        return userCompany
    }

    fun getUserWebsite(): MutableLiveData<String> {
        return userWebsite
    }

    fun getUserLng(): MutableLiveData<String> {
        return userLang
    }


    fun getuser(): MutableLiveData<User> {
        return user
    }

    fun redirectToMap(view: androidx.appcompat.widget.AppCompatImageButton, lat: String, lng: String) {
        val intent = Intent(view.context, MapsActivity::class.java)
        intent.putExtra(LAT, lat)
        intent.putExtra(LNG, lng)
        view.context.startActivity(intent)
    }
}