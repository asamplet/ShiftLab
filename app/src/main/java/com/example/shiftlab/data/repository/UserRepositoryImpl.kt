package com.example.shiftlab.data.repository

import com.example.shiftlab.data.datasource.UserDataSource
import com.example.shiftlab.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDataSource: UserDataSource,
) : UserRepository {

    override fun getUserData(): String = userDataSource.getUser()

    override fun setUserData(user: String) {
        userDataSource.setUser(user)
    }
}