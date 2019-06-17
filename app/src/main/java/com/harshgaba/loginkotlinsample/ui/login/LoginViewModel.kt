package com.harshgaba.loginkotlinsample.ui.login

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.harshgaba.loginkotlinsample.common.BaseViewModel
import com.harshgaba.loginkotlinsample.database.CredentialsDAO
import com.harshgaba.loginkotlinsample.models.login.LoginForm
import android.widget.EditText
import androidx.annotation.VisibleForTesting
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.harshgaba.loginkotlinsample.R
import com.harshgaba.loginkotlinsample.database.Credentials
import com.harshgaba.loginkotlinsample.models.login.LoginFields
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by Harsh Gaba on 2019-06-17.
 * harshgaba08@gmail.com
 */

class LoginViewModel(private val credentialsDAO: CredentialsDAO) : BaseViewModel() {

    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val showCountryPicker: MutableLiveData<Int> = MutableLiveData()
    val loginSuccessfull: MutableLiveData<Credentials> = MutableLiveData()
    val login: LoginForm = LoginForm()
    var onFocusEmail: View.OnFocusChangeListener? = null
    var onFocusPassword: View.OnFocusChangeListener? = null
    //    var onFocusUsername: View.OnFocusChangeListener? = null
    private lateinit var checkCredentialsSubscription: Disposable
    private lateinit var save: Disposable

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

        save = Observable.fromCallable {
            credentialsDAO.insertCredentials(
                Credentials(
                    "test@car.com",
                    "CarTrack",
                    "123456",
                    "Albania"
                )
            )
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun onButtonClick() {
        login.onClick()
    }

    fun onCountrySelectorClick() {
        showCountryPicker.value = null
    }

    fun updateSelectedCountry(country: String) {
        login.fields.country = country
    }


    fun getLoginFields(): MutableLiveData<LoginFields> {
        return login.getLoginFields()
    }

    companion object {

        @BindingAdapter("error")
        @JvmStatic
        fun setError(inputLayout: TextInputLayout, strOrResId: Any?) {
            if (strOrResId is Int) {
                inputLayout.error = inputLayout.context.getString(strOrResId)
            } else if (strOrResId is String) {
                inputLayout.error = strOrResId as String
            } else {
                inputLayout.error = null
            }
        }

        @BindingAdapter("onFocus")
        @JvmStatic
        fun bindFocusChange(editText: EditText, onFocusChangeListener: View.OnFocusChangeListener) {
            if (editText.onFocusChangeListener == null) {
                editText.onFocusChangeListener = onFocusChangeListener
            }
        }
    }

    /**
     *
     */
    fun checkCredentials(loginFields: LoginFields) {

        checkCredentialsSubscription =
            Observable.fromCallable {
                loginFields.email?.let {
                    loginFields.password?.let { it1 ->
                        loginFields.country?.let { it2 ->
                            credentialsDAO.checkCredentials(
                                it,
                                it1, it2
                            )
                        }
                    }
                }
            }
                .map { credentials: Credentials -> credentials }
                .doOnNext { credentials: Credentials ->
                    if (null == credentials) {
                        onLoginError()
                    }
                }
                .filter { credentials: Credentials -> null != credentials }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ credentials -> onLoginSuccess(credentials) },
                    { onLoginError() })
    }


    private fun onLoginSuccess(credentials: Credentials) {
        loginSuccessfull.value = credentials
    }

    private fun onLoginError() {
        errorMessage.value = R.string.error_login_failed
    }

    override fun onCleared() {
        super.onCleared()
        checkCredentialsSubscription.dispose()
        save.dispose()
    }
}