package com.wonjo.deepfine.core.data.auth

import com.wonjo.deepfine.core.data.auth.local.AuthUserDao
import com.wonjo.deepfine.core.data.auth.local.AuthUserEntity
import com.wonjo.deepfine.core.domain.auth.model.AuthUser
import com.wonjo.deepfine.core.domain.auth.repository.AuthRepository
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val authUserDao: AuthUserDao,
    private val passwordHasher: PasswordHasher,
) : AuthRepository {
    override suspend fun findUserByEmail(email: String): AuthUser? =
        authUserDao.findByEmail(email)?.toDomain()

    override suspend fun isPasswordMatched(
        email: String,
        password: String,
    ): Boolean {
        val user = authUserDao.findByEmail(email) ?: return false
        return user.passwordHash == passwordHasher.hash(email, password)
    }

    override suspend fun saveUser(
        email: String,
        name: String,
        password: String,
    ) {
        authUserDao.upsert(
            AuthUserEntity(
                email = email,
                name = name,
                passwordHash = passwordHasher.hash(email, password),
            ),
        )
    }
}

private fun AuthUserEntity.toDomain(): AuthUser =
    AuthUser(
        email = email,
        name = name,
    )
