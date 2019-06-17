package com.harshgaba.loginkotlinsample.ui.login

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import com.harshgaba.loginkotlinsample.R
import com.harshgaba.loginkotlinsample.dagger.factory.ViewModelFactory
import androidx.lifecycle.Observer
import com.harshgaba.loginkotlinsample.databinding.FragmentLoginBinding


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
            viewModel.init();
        }
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel
        return loginFragmentView
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}