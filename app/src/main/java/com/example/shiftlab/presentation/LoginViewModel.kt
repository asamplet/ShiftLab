package com.example.shiftlab.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shiftlab.domain.usecase.GetUserDataUseCase
import com.example.shiftlab.domain.usecase.SetUserDataUseCase

const val NAME_ERROR = 1
const val SURNAME_ERROR = 2
const val PASSWORD_ERROR = 3
const val PASSWORD_ERROR_REPEAT = 4
const val CORRECT = 5

class LoginViewModel(
    private val router: LoginRouter,
    private val setUserDataUseCase: SetUserDataUseCase,
    private val getUserDataUseCase: GetUserDataUseCase,
) : ViewModel() {

    fun navigateToMainScreen() {
        setUserDataUseCase(requireNotNull(nameLiveData.value))
        router.openMain()
    }

    fun preStartScreen() {
        if (getUserDataUseCase() != "empty") {
            router.openMain()
        }
    }

    val nameLiveData = MutableLiveData<String>()
    val surnameLiveData = MutableLiveData<String>()
    val dateLiveData = MutableLiveData<String>()
    val passLiveData = MutableLiveData<String>()
    val passRepLiveData = MutableLiveData<String>()
    val errorLiveData = MutableLiveData<Int>()
    private var correctValid: Boolean = true

    fun validCheck() {
        correctValid = true
        if (nameLiveData.value?.all { it.isLetter() } == false
            || nameLiveData.value?.any { it.isWhitespace() } == true
        ) {
            errorLiveData.value = NAME_ERROR
            correctValid = false
        }
        if (surnameLiveData.value?.all { it.isLetter() } == false
            || surnameLiveData.value?.any { it.isWhitespace() } == true
        ) {
            errorLiveData.value = SURNAME_ERROR
            correctValid = false
        }
        if (passLiveData.value?.any { it.isDigit() } == false
            || passLiveData.value?.any { it.isLowerCase() } == false
            || passLiveData.value?.any { it.isUpperCase() } == false
        ) {
            errorLiveData.value = PASSWORD_ERROR
            correctValid = false
        }
        if (passLiveData.value != passRepLiveData.value) {
            errorLiveData.value = PASSWORD_ERROR_REPEAT
            correctValid = false
        }
        if (correctValid) {
            errorLiveData.value = CORRECT
        }
    }

    fun fieldCheck(): Boolean {
        return !(nameLiveData.value.isNullOrEmpty()
                || surnameLiveData.value.isNullOrEmpty()
                || dateLiveData.value.isNullOrEmpty()
                || passLiveData.value.isNullOrEmpty()
                || passRepLiveData.value.isNullOrEmpty())
    }

}