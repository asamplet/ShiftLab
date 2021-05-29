package com.example.shiftlab.di

import com.example.shiftlab.presentation.LoginRouter
import com.example.shiftlab.ui.router.LoginRouterImpl
import org.koin.dsl.module

val routerModule = module {
    factory<LoginRouter> { LoginRouterImpl(get()) }
}