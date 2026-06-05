package com.wonjo.deepfine.core.data.auth.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "auth_users")
internal data class AuthUserEntity(
    @PrimaryKey val email: String,
    val name: String,
    val passwordHash: String,
)
