package com.example.plasticmandi.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.plasticmandi.repository.AllOfferRepository
import com.example.plasticmandi.repository.AuthRepository
import com.example.plasticmandi.repository.SplashRepository
import com.example.plasticmandi.viewmodel.AllOffersViewModel
import com.example.plasticmandi.viewmodel.AuthViewModel
import com.example.plasticmandi.viewmodel.SplashViewModel

class AuthViewModelFactory(private val authRepository: AuthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(authRepository) as T;
    }
}

class SplashViewModelFactory(private val splashRepository: SplashRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SplashViewModel(splashRepository) as T;
    }
}

class AllOffersViewModelFactory(private val allOffersRepository: AllOfferRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AllOffersViewModel(allOffersRepository) as T;
    }
}