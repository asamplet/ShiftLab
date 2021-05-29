package com.example.shiftlab.di

import com.example.shiftlab.presentation.LoginViewModel
import com.example.shiftlab.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel {
        LoginViewModel(
            router = get(),
            setUserDataUseCase = get(),
            getUserDataUseCase = get(),
        )
    }
    viewModel {
        MainViewModel(
            router = get(),
            getUserDataUseCase = get(),
            setUserDataUseCase = get(),
        )
    }

}