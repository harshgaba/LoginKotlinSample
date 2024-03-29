package com.harshgaba.loginkotlinsample.models.login

import android.text.TextUtils
import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.harshgaba.loginkotlinsample.BR
import com.harshgaba.loginkotlinsample.R




/**
 * Created by Harsh Gaba on 2019-06-17.
 * harshgaba08@gmail.com
 */

class LoginForm:BaseObservable(){
    val fields:LoginFields=LoginFields()
    val errorFields:LoginErrorFields=LoginErrorFields()
    val buttonClick: MutableLiveData<LoginFields> = MutableLiveData()


    @Bindable
    public fun isValid():Boolean{
        var valid :Boolean= isEmailValid(false)
        valid = isPasswordValid(false) && valid
        valid=isCountryValid(false) && valid
        notifyPropertyChanged(BR.emailError)
        notifyPropertyChanged(BR.passwordError)
        return valid
    }

    public fun isEmailValid(setMessage:Boolean):Boolean {
        // Minimum a@b.c
        var email: String? = fields?.email
        if (email != null && email.length > 5) {

            var indexOfAt:Int = email.indexOf("@")
            var indexOfDot:Int = email.lastIndexOf(".")
            if (indexOfAt > 0 && indexOfDot > indexOfAt && indexOfDot < email.length - 1) {
                errorFields.email=null
                notifyPropertyChanged(BR.valid);
                return true;
            } else {
                if (setMessage) {
                    errorFields.email=R.string.error_format_invalid
                    notifyPropertyChanged(BR.valid);
                }
                return false;
            }
        }
        if (setMessage) {
            errorFields.email=R.string.error_too_short
            notifyPropertyChanged(BR.valid);
        }

        return false;
    }


    public fun isPasswordValid( setMessage:Boolean):Boolean {
        var password: String? = fields?.password
        if (password != null && password.length > 5) {
            errorFields.password=null
            notifyPropertyChanged(BR.valid);
            return true;
        } else {
            if (setMessage) {
                errorFields.password=R.string.password_too_short
                notifyPropertyChanged(BR.valid);
            }

            return false;
        }
    }

    public fun isCountryValid( setMessage:Boolean):Boolean {
        var country: String? = fields?.country
        if (!TextUtils.isEmpty(country)) {
            errorFields.country=null
            notifyPropertyChanged(BR.valid);
            return true;
        } else {
            if (setMessage) {
                errorFields.country=R.string.invalid_country
                notifyPropertyChanged(BR.valid);
            }

            return false;
        }
    }

    public fun onClick() {
        if (isValid()) {
            buttonClick.setValue(fields);
        }
    }

    @Bindable
    public fun getEmailError(): Int? {
        return errorFields.email
    }

    @Bindable
    public fun getPasswordError(): Int? {
        return errorFields.password
    }


    fun getLoginFields(): MutableLiveData<LoginFields> {
        return buttonClick
    }

}