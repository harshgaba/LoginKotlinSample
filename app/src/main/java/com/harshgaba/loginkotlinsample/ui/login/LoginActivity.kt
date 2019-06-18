package com.harshgaba.loginkotlinsample.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.harshgaba.loginkotlinsample.R


/**
 * Created by Harsh Gaba on 2019-06-17.
 * harshgaba08@gmail.com
 */


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportFragmentManager.beginTransaction().add(R.id.frag_container, LoginFragment()).commit()

    }
}
