package com.harshgaba.loginkotlinsample.network

import com.harshgaba.loginkotlinsample.models.Users
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to get result of webservices
 */
interface ApiClient {
    /**
     * Get the list of the pots from the API
     */
    @GET("/users")
    fun getUsersList(): Observable<List<Users>>
}