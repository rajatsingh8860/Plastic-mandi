package com.example.plasticmandi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plasticmandi.model.User
import com.example.plasticmandi.repository.SplashRepository
import kotlinx.coroutines.launch

class SplashViewModel(private val repository: SplashRepository) : ViewModel() {

    lateinit var user: LiveData<User>

    init {
        getUser()
    }

    private fun getUser() {
        user = repository.getUser()

    }
}