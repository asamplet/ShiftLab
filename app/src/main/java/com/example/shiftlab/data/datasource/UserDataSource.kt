package com.example.shiftlab.data.datasource

import android.content.Context

interface UserDataSource {

    fun getUser(): String
    fun setUser(user: String)
}

class UserDataSourceImpl(
    context: Context,
) : UserDataSource {

    private val userId = context.getSharedPreferences(USER, Context.MODE_PRIVATE)

    private companion object {

        const val USER = "EmptyUser"
        const val CURRENT_USER = "user"
        const val DEFAULT_VALUE = "empty"
    }

    override fun getUser(): String = userId.getString(CURRENT_USER, DEFAULT_VALUE).toString()

    override fun setUser(user: String) {
        val editor = userId.edit()
        editor.putString(CURRENT_USER, user)
        editor.apply()
    }
}