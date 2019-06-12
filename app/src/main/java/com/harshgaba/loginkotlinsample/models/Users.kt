package com.harshgaba.loginkotlinsample.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Harsh Gaba on 2019-06-12.
 * harshgaba08@gmail.com
 */

class Users {
    @SerializedName("id")
    @Expose
    var id: Int = 0
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("username")
    @Expose
    var username: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("address")
    @Expose
    var address: Address? = null
    @SerializedName("phone")
    @Expose
    var phone: String? = null
    @SerializedName("website")
    @Expose
    var website: String? = null
    @SerializedName("company")
    @Expose
    var company: Company? = null
}