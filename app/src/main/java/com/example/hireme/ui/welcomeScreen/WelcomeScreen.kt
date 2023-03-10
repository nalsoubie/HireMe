package com.example.hireme.ui.welcomeScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.hireme.R
import com.example.hireme.databinding.ActivityWelcomeScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeScreen : AppCompatActivity() {
    private val viewModel: WelcomeScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_screen)

        val binding: ActivityWelcomeScreenBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_welcome_screen)

        binding.viewmodel = viewModel
    }
}