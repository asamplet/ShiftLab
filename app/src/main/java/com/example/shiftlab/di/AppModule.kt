package com.example.shiftlab.di

import com.example.shiftlab.data.datasource.UserDataSource
import com.example.shiftlab.data.datasource.UserDataSourceImpl
import com.example.shiftlab.data.repository.UserRepositoryImpl
import com.example.shiftlab.domain.repository.UserRepository
import com.example.shiftlab.domain.usecase.GetUserDataUseCase
import com.example.shiftlab.domain.usecase.SetUserDataUseCase
import com.example.shiftlab.ui.buildCicerone
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.dsl.module

val appModule = module {
    single { buildCicerone() }
    single { get<Cicerone<Router>>().router }
    single { get<Cicerone<Router>>().getNavigatorHolder() }

    factory { GetUserDataUseCase(get()) }
    factory { SetUserDataUseCase(get()) }

    factory<UserDataSource> { UserDataSourceImpl(get()) }
    factory<UserRepository> { UserRepositoryImpl(userDataSource = get()) }
}