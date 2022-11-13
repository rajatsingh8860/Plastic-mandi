package com.example.plasticmandi.repository

import androidx.lifecycle.LiveData
import com.example.plasticmandi.db.UserDatabase
import com.example.plasticmandi.model.User

class SplashRepository(private val db: UserDatabase) {

    fun getUser(): LiveData<User> {
        return db.getUserDao().getUser()
    }
}