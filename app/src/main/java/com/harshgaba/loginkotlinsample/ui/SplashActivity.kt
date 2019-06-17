package com.harshgaba.loginkotlinsample.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.harshgaba.loginkotlinsample.R
import com.harshgaba.loginkotlinsample.ui.login.LoginActivity


/**
 * Created by Harsh Gaba on 2019-06-18.
 * harshgaba08@gmail.com
 */

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({ launchAuthentication() }, 1500)

    }

    fun launchAuthentication(){
        val intent = Intent(this, LoginActivity::class.java)
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, findViewById(R.id.imageview_app_icon) as View, "icon")
        startActivity(intent, options.toBundle())
        finish()
    }
}