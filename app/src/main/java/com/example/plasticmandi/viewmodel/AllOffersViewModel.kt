package com.example.plasticmandi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plasticmandi.model.response.AllOffers.AllOffersResponse
import com.example.plasticmandi.repository.AllOfferRepository
import com.example.plasticmandi.utils.Resource
import kotlinx.coroutines.launch
import java.util.ResourceBundle

class AllOffersViewModel(val repository: AllOfferRepository) : ViewModel() {
    val allOffersResponse: MutableLiveData<Resource<AllOffersResponse>> = MutableLiveData()

    init {
        getAllOffers()
    }

    private fun getAllOffers() = viewModelScope.launch {
        allOffersResponse.postValue(Resource.Loading())
        val response = repository.getAllOffers()
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                allOffersResponse.postValue(Resource.Success(resultResponse, response.code()))
            }
        } else {
            allOffersResponse.postValue(Resource.Error(response.message(), response.code()))
        }
    }
}