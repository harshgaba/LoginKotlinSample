package com.harshgaba.loginkotlinsample.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context


/**
 * Created by Harsh Gaba on 2019-06-17.
 * harshgaba08@gmail.com
 */


/**
 * The Room database that contains the Credentials table
 */
@Database(entities = arrayOf(Credentials::class), version = 1)
abstract class CredentialsDatabase : RoomDatabase() {

    abstract fun credentialsDAO(): CredentialsDAO

    companion object {

        @Volatile private var INSTANCE: CredentialsDatabase? = null

        fun getInstance(context: Context): CredentialsDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                CredentialsDatabase::class.java, "Credentials.db")
                .build()
    }
}