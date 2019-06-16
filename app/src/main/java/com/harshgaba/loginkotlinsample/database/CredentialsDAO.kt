package com.harshgaba.loginkotlinsample.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable


/**
 * Created by Harsh Gaba on 2019-06-17.
 * harshgaba08@gmail.com
 */


/**
 * Data Access Object for the Credentials table.
 */
@Dao
interface CredentialsDAO {

    /**
     * Get a credential by email.
     * @return the credential from the table with a specific email.
     */
    @Query("SELECT * FROM Credentials WHERE email = :email")
    fun getCredentialByEmail(email: String): Flowable<Credentials>

    /**
     * Insert the credential in the database. If the credential already exists, replace it.
     * @param credential the credential to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCredentials(credential: Credentials): Completable

    /**
     * Delete all Credentials.
     */
    @Query("DELETE FROM Credentials")
    fun deleteAllCredentials()
}