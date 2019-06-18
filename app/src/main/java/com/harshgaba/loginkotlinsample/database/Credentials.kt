package com.harshgaba.loginkotlinsample.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


/**
 * Created by Harsh Gaba on 2019-06-17.
 * harshgaba08@gmail.com
 */

@Entity(tableName = "credentials")
data class Credentials(@PrimaryKey
                @ColumnInfo(name = "email")
                val email: String,
                @ColumnInfo(name = "username")
                val userName: String,
                @ColumnInfo(name = "password")
                val password: String,
                @ColumnInfo(name = "country")
                val country: String)