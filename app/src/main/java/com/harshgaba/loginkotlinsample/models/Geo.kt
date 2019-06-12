package com.harshgaba.loginkotlinsample.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Harsh Gaba on 2019-06-12.
 * harshgaba08@gmail.com
 */


class Geo {

    @SerializedName("lat")
    @Expose
    var lat: String? = null
    @SerializedName("lng")
    @Expose
    var lng: String? = null

}