package com.example.shiftlab.ui

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

fun buildCicerone(): Cicerone<Router> =
    Cicerone.create().also {
        it.router.navigateTo(getLoginScreen())
    }