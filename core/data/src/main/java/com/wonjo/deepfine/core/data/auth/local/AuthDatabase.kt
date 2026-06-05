package com.wonjo.deepfine.core.data.auth.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [AuthUserEntity::class],
    version = 1,
    exportSchema = false,
)
internal abstract class AuthDatabase : RoomDatabase() {
    abstract fun authUserDao(): AuthUserDao
}
