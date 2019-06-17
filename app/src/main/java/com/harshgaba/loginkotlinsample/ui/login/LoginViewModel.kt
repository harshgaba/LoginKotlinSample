package com.harshgaba.loginkotlinsample.ui.login

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.harshgaba.loginkotlinsample.common.BaseViewModel
import com.harshgaba.loginkotlinsample.database.CredentialsDAO
import com.harshgaba.loginkotlinsample.models.login.LoginForm
import android.widget.EditText
import androidx.annotation.VisibleForTesting
import androidx.databinding.BindingAdapter






/**
 * Created by Harsh Gaba on 2019-06-17.
 * harshgaba08@gmail.com
 */

class LoginViewModel(private val credentialsDAO: CredentialsDAO) : BaseViewModel() {

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val login: LoginForm = LoginForm()
    var onFocusEmail: View.OnFocusChangeListener? = null
    var onFocusPassword: View.OnFocusChangeListener? = null
    var onFocusUsername: View.OnFocusChangeListener? = null

    @VisibleForTesting
    fun init() {
        onFocusEmail = View.OnFocusChangeListener { view, focused ->
            val et = view as EditText
            if (et.text.length > 0 && !focused) {
                login.isEmailValid(true)
            }
        }

        onFocusPassword = View.OnFocusChangeListener { view, focused ->
            val et = view as EditText
            if (et.text.length > 0 && !focused) {
                login.isPasswordValid(true)
            }
        }

        onFocusUsername = View.OnFocusChangeListener { view, focused ->
            val et = view as EditText
            if (et.text.length > 0 && !focused) {
                login.isPasswordValid(true)
            }
        }
    }

    fun onButtonClick() {
        login.onClick()
    }

    @BindingAdapter("error")
    fun setError(editText: EditText, strOrResId: Any) {
        if (strOrResId is Int) {
            editText.error = editText.context.getString(strOrResId)
        } else {
            editText.error = strOrResId as String
        }
    }

    @BindingAdapter("onFocus")
    fun bindFocusChange(editText: EditText, onFocusChangeListener: View.OnFocusChangeListener) {
        if (editText.onFocusChangeListener == null) {
            editText.onFocusChangeListener = onFocusChangeListener
        }
    }



}