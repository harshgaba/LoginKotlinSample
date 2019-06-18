package com.harshgaba.loginkotlinsample.ui.login

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.harshgaba.loginkotlinsample.R
import com.harshgaba.loginkotlinsample.dagger.factory.ViewModelFactory
import androidx.lifecycle.Observer
import com.harshgaba.loginkotlinsample.database.Credentials
import com.harshgaba.loginkotlinsample.databinding.FragmentLoginBinding
import com.harshgaba.loginkotlinsample.models.login.LoginFields
import com.harshgaba.loginkotlinsample.ui.users.UsersListActivity
import com.harshgaba.loginkotlinsample.utils.KeyboardUtils
import com.hendraanggrian.appcompat.countrypicker.Country
import com.hendraanggrian.appcompat.countrypicker.CountryPickerSheetDialog
import com.hendraanggrian.appcompat.countrypicker.CountryPickerSheetDialogFragment


/**
 * Created by Harsh Gaba on 2019-06-17.
 * harshgaba08@gmail.com
 */

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private var errorSnackbar: Snackbar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        var loginFragmentView: View = binding.root


        // setting values to model
        viewModel = ViewModelProviders.of(this, context?.applicationContext?.let { ViewModelFactory(it) })
            .get(LoginViewModel::class.java)
        if (savedInstanceState == null) {
            viewModel.init()
        }
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        viewModel.showCountryPicker.observe(this, Observer { empty ->

            val countryPickerDialog: CountryPickerSheetDialog? = context?.let { CountryPickerSheetDialog(it) }
            countryPickerDialog?.setFlagShown(false)
            countryPickerDialog?.setOnSelectedListener { country: Country ->
                run {
                    binding.textviewSelectedCountry.text = country.getName(binding.textviewSelectedCountry.context)
                    viewModel.updateSelectedCountry(country.getName(binding.textviewSelectedCountry.context))
                }
            }
            countryPickerDialog?.show()
        })
        binding.viewModel = viewModel
        setupLoginClick()
        binding.inputEmail.requestFocus()
        return loginFragmentView
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_SHORT)
        errorSnackbar?.show()
        binding.buttonLogin.revertAnimation { binding.buttonLogin.setBackgroundResource(R.drawable.circular_border_shape) }
        KeyboardUtils.showKeyboardFrom(binding.inputPassword.context, binding.inputPassword)
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }

    private fun setupLoginClick() {
        viewModel.getLoginFields().observe(this,
            Observer<LoginFields> { loginFields ->
                binding.buttonLogin.requestFocus()
                KeyboardUtils.hideKeyboardFrom(binding.buttonLogin.context, binding.buttonLogin)
                binding.buttonLogin.startAnimation {
                    Handler().postDelayed({ viewModel.checkCredentials(loginFields) }, 1000)
                }
            })

        viewModel.loginSuccessfull.observe(this, Observer<Credentials> { credentials ->

            context?.let { defaultColor(it) }?.let {
                binding.buttonLogin.doneLoadingAnimation(
                    it, defaultDoneImage(
                        context!!.resources
                    )
                )
            }

            val intent = Intent(this.activity?.applicationContext, UsersListActivity::class.java)
            startActivity(intent)
            this.activity?.finish()

        })
    }


    private fun defaultColor(context: Context) = ContextCompat.getColor(context, R.color.colorAccent)

    private fun defaultDoneImage(resources: Resources) =
        BitmapFactory.decodeResource(resources, R.drawable.ic_done_white_48dp)
}