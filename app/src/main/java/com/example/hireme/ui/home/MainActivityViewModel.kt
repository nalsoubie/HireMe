package com.example.hireme.ui.home

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.hireme.data.Repository
import com.example.hireme.utils.startLoginActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(

    private val repository: Repository

    ) : ViewModel() {

    val user by lazy {
        repository.currentUser()
    }

    fun logout(view: View) {
        repository.logout()
        view.context.startLoginActivity()
    }
}
