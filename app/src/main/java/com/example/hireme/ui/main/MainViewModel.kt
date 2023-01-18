package com.example.hireme.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hireme.data.Repository
import com.example.hireme.model.Job
import com.example.hireme.utils.DataHolder
import com.example.hireme.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _jobs = MutableLiveData<Resource<List<Job>>>()
    val jobs get() = _jobs

    fun getJobs() = viewModelScope.launch {
        repository.getJobs(object : DataHolder {
            override fun <T : Any> hold(data: T) {
                _jobs.postValue(data as Resource<List<Job>>)
            }
        })
    }


}