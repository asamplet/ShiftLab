package com.example.shiftlab.domain.usecase

import com.example.shiftlab.domain.repository.UserRepository

class SetUserDataUseCase(
    private val userRepository: UserRepository
) {

    operator fun invoke(user: String){
        userRepository.setUserData(user)
    }
}