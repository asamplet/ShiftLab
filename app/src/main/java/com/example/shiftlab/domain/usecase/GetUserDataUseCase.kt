package com.example.shiftlab.domain.usecase

import com.example.shiftlab.domain.repository.UserRepository

class GetUserDataUseCase(
    private val userRepository: UserRepository
) {

    operator fun invoke(): String = userRepository.getUserData()
}