package com.example.hireme.ui.username

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.hireme.R
import com.example.hireme.data.AuthListener
import com.example.hireme.data.Repository
import com.example.hireme.ui.home.MainActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.muddz.styleabletoast.StyleableToast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class UsernameViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    var username: String? = null
    var authListener: AuthListener? = null
    private val disposables = CompositeDisposable()
    private val user by lazy { repository.currentUser() }


    private fun updateUsername() {
        val disposable =
            repository.updateUsername(user!!.uid, username!!.trim())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("updateUsername","Errorrrrrrrrrr")
                    authListener?.onSuccess()

                }, {
                    Log.d("updateUsername","orrrrrrrrr")
                    authListener?.onFailure(it.message!!)
                }
                )
        disposables.add(disposable)





    }
    fun goToHome(view: View) {
        updateUsername()
        StyleableToast.makeText(view.context, "Signup Success!!", Toast.LENGTH_LONG, R.style.mytoast).show()
        Intent(view.context, MainActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

}