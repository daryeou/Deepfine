package com.wonjo.deepfine.core.domain.auth.usecase

import com.wonjo.deepfine.core.domain.auth.repository.AuthRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(
        email: String,
        name: String,
        password: String,
    ) {
        authRepository.saveUser(
            email = email,
            name = name,
            password = password,
        )
    }
}
