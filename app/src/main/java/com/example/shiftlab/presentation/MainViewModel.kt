package com.example.shiftlab.presentation

import androidx.lifecycle.ViewModel
import com.example.shiftlab.domain.usecase.GetUserDataUseCase
import com.example.shiftlab.domain.usecase.SetUserDataUseCase

class MainViewModel(
    private val router: LoginRouter,
    private val getUserDataUseCase: GetUserDataUseCase,
    private val setUserDataUseCase: SetUserDataUseCase,
) : ViewModel() {

    fun getUser(): String = getUserDataUseCase()

    fun goBack() {
        setUserDataUseCase("empty")
        router.openLogin()
    }

}