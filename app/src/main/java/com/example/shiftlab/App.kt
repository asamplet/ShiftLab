package com.example.shiftlab

import android.app.Application
import android.bluetooth.BluetoothAdapter.ERROR
import com.example.shiftlab.di.appModule
import com.example.shiftlab.di.loginModule
import com.example.shiftlab.di.routerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                appModule,
                loginModule,
                routerModule,
            )
        }
    }
}