package com.harshgaba.loginkotlinsample.network

import com.harshgaba.loginkotlinsample.models.User
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Harsh Gaba on 2019-06-12.
 * harshgaba08@gmail.com
 */

/**
 * The interface which provides methods to get result of webservices
 */
interface ApiClient {
    /**
     * Get the list of the pots from the API
     */
    @GET("/users")
    fun getUsersList(): Observable<List<User>>
}