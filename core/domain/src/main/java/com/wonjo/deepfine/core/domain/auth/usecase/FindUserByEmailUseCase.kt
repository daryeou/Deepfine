package com.wonjo.deepfine.core.domain.auth.usecase

import com.wonjo.deepfine.core.domain.auth.model.AuthUser
import com.wonjo.deepfine.core.domain.auth.repository.AuthRepository
import javax.inject.Inject

class FindUserByEmailUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(email: String): AuthUser? =
        authRepository.findUserByEmail(email)
}
