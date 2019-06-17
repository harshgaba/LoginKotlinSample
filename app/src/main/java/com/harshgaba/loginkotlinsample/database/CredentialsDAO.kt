package com.harshgaba.loginkotlinsample.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query



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
    fun getCredentialByEmail(email: String): Credentials

    /**
     * Insert the credential in the database. If the credential already exists, replace it.
     * @param credential the credential to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCredentials(credential: Credentials)

    /**
     * Delete all Credentials.
     */
    @Query("DELETE FROM Credentials")
    fun deleteAllCredentials()
}