package com.example.hireme.ui.profile

import android.net.Uri
import androidx.lifecycle.*
import com.example.hireme.data.AuthListener
import com.example.hireme.data.Repository
import com.example.hireme.model.Experience
import com.example.hireme.model.PortfolioItem
import com.example.hireme.model.User
import com.example.hireme.utils.Resource
import com.example.hireme.utils.DataHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
@HiltViewModel
class ProfileViewModel
@Inject
constructor(private val repository: Repository) : ViewModel() {

    val userData = MutableLiveData<Resource<User>>()

    private val _experienceData = MutableLiveData<Resource<List<Experience>>>()
    val experienceData get() = _experienceData

    private val _portfolioItems = MutableLiveData<Resource<List<PortfolioItem>>>()
    val portfolioItems get() = _portfolioItems

    var authListener: AuthListener? = null
    private val disposables = CompositeDisposable()


    fun uploadPictureToFirebaseStorage(uri: Uri?, folder: String) {
        val disposable = repository.addImageToStorage(uri!!, folder)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                authListener?.onSuccess()
            }, {
                authListener?.onFailure(it.message!!)
            })
        disposables.add(disposable)


    }

    fun getUserExperience() {
        viewModelScope.launch {
            repository.getUserExperience(object : DataHolder {
                override fun <T : Any> hold(data: T) {
                    _experienceData.postValue(data as Resource<List<Experience>>)
                }

            })
        }
    }

    fun getUserData() {
        viewModelScope.launch {
            repository.getUserData(object : DataHolder {
                override fun <T : Any> hold(data: T) {
                    userData.postValue(data as Resource<User>)
                }
            })
        }
    }

    fun addExperience(title: String, place: String, from: String, to: String, duration: Long) {
        val exp = Experience("", title, "$duration years", from, to, place)
        viewModelScope.launch {
            repository.addExperience(exp)
        }
    }

    fun deleteExperienceItem(itemId: String) {
        viewModelScope.launch {
            repository.deleteExperienceItem(itemId)
        }
    }

    fun fetchPortfolioItems() = viewModelScope.launch {
        repository.getPortfolioItems(object : DataHolder {
            override fun <T : Any> hold(data: T) {
                _portfolioItems.postValue(data as Resource<List<PortfolioItem>>)
            }
        })
    }

    fun addPortfolioItem(title: String, image: String) {
        val item = PortfolioItem("", title, image)
        viewModelScope.launch {
            repository.addPortfolioItem(item)
        }
    }

    fun deletePortfolioItem(itemId: String) {
        viewModelScope.launch {
            repository.deletePortfolioItem(itemId)
        }
    }
}
