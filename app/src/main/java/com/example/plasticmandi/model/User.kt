package com.example.plasticmandi.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "user"
)
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val firstName: String,
    val lastName: String,
    val code: String,
    val phone: String,
    val email: String,
    val accessToken: String
)
