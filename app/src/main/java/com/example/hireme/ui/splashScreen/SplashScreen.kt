package com.example.hireme.ui.splashScreen

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.hireme.R
import com.example.hireme.utils.startHomeActivity
import com.example.hireme.utils.startWelcomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreen : AppCompatActivity(){

    private val viewModel: SplashScreenViewModel by viewModels()




    override fun onCreate(savedInstanceState: Bundle?) {
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        setupViewModel()


    }

    private fun setupViewModel() {



        viewModel.liveData.observe(this, Observer {

            when (it) {
                is SplashState.SignUpActivity -> {
                    finish()
                    startWelcomeActivity()
                }
            }

        })

    }

    override fun onStart() {
        super.onStart()

        viewModel.user?.let {
            startHomeActivity()

        }
    }


}
