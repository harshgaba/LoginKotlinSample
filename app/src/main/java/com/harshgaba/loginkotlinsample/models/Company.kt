package com.harshgaba.loginkotlinsample.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Harsh Gaba on 2019-06-12.
 * harshgaba08@gmail.com
 */

class Company {
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("catchPhrase")
    @Expose
    var catchPhrase: String? = null
    @SerializedName("bs")
    @Expose
    var bs: String? = null
}