package com.example.shiftlab.ui.router

import com.example.shiftlab.presentation.LoginRouter
import com.example.shiftlab.ui.getLoginScreen
import com.example.shiftlab.ui.getMainScreen
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class LoginRouterImpl(
    private val router: Router,
) : LoginRouter{

    override fun openMain() {
        router.newRootScreen(getMainScreen())
    }

    override fun openLogin() {
        router.newRootScreen(getLoginScreen())
    }
}