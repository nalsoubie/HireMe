package com.example.hireme.ui.splashScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hireme.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashScreenViewModel
@Inject
constructor
    (private val repository: Repository) : ViewModel() {


    val user by lazy { repository.currentUser() }
    val liveData: LiveData<SplashState>
        get() = mutableLiveData
    private val mutableLiveData = MutableLiveData<SplashState>()

    init {
        GlobalScope.launch {
            kotlinx.coroutines.delay(5000)
            mutableLiveData.postValue(SplashState.SignUpActivity)

        }
    }


}

