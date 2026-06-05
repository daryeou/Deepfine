package com.wonjo.deepfine.core.data.auth.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
internal interface AuthUserDao {
    @Query("SELECT * FROM auth_users WHERE email = :email LIMIT 1")
    suspend fun findByEmail(email: String): AuthUserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(user: AuthUserEntity)
}
