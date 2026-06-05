package com.wonjo.deepfine.core.domain.auth.usecase

import com.wonjo.deepfine.core.domain.auth.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
    ): Boolean = authRepository.isPasswordMatched(email, password)
}
