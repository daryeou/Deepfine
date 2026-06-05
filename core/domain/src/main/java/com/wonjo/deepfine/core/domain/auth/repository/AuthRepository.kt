package com.wonjo.deepfine.core.domain.auth.repository

import com.wonjo.deepfine.core.domain.auth.model.AuthUser

interface AuthRepository {
    suspend fun findUserByEmail(email: String): AuthUser?

    suspend fun isPasswordMatched(email: String, password: String): Boolean

    suspend fun saveUser(
        email: String,
        name: String,
        password: String,
    )
}
