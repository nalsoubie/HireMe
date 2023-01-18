package com.example.hireme.ui.username

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.hireme.R
import com.example.hireme.data.AuthListener
import com.example.hireme.databinding.ActivityUsernameBinding
import dagger.hilt.android.AndroidEntryPoint
import io.github.muddz.styleabletoast.StyleableToast

@AndroidEntryPoint
class Username : AppCompatActivity(), AuthListener {


    private val viewModel: UsernameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_username)


        val binding: ActivityUsernameBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_username)
        binding.viewmodel = viewModel

        viewModel.authListener = this


    }

    override fun onStarted() {
    }

    override fun onSuccess() {
    }


    override fun onFailure(message: String) {
    }
}