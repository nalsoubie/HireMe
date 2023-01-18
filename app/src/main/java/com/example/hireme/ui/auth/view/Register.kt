package com.example.hireme.ui.auth.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.hireme.R
import com.example.hireme.data.AuthListener
import com.example.hireme.databinding.ActivitySignUpBinding
import com.example.hireme.ui.auth.viewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Register : AppCompatActivity(), AuthListener {


    private val viewModel: AuthViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val binding: ActivitySignUpBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        binding.viewmodel = viewModel

        viewModel.authListener = this
    }

    override fun onStarted() {
    }

    override fun onSuccess() {
        viewModel.goToUsernameActivity(this)
    }

    override fun onFailure(message: String) {
        Log.d("on Failure",message)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}