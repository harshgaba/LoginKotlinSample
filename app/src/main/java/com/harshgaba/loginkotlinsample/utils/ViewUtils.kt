package com.harshgaba.loginkotlinsample.utils

import android.content.ContextWrapper
import android.support.v7.app.AppCompatActivity
import android.view.View


/**
 * Created by Harsh Gaba on 2019-06-12.
 * harshgaba08@gmail.com
 */

fun View.getParentActivity(): AppCompatActivity?{
    var context = this.context
    while (context is ContextWrapper) {
        if (context is AppCompatActivity) {
            return context
        }
        context = context.baseContext
    }
    return null
}