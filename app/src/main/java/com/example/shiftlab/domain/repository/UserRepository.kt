package com.example.shiftlab.domain.repository

interface UserRepository {

    fun getUserData(): String
    fun setUserData(user: String)
}