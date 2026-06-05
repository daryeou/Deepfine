package com.wonjo.deepfine.core.data.auth.di

import android.content.Context
import androidx.room.Room
import com.wonjo.deepfine.core.data.auth.local.AuthDatabase
import com.wonjo.deepfine.core.data.auth.local.AuthUserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AuthDatabaseModule {
    @Provides
    @Singleton
    fun provideAuthDatabase(
        @ApplicationContext context: Context,
    ): AuthDatabase =
        Room.databaseBuilder(
            context,
            AuthDatabase::class.java,
            AuthDatabaseName,
        ).build()

    @Provides
    fun provideAuthUserDao(authDatabase: AuthDatabase): AuthUserDao =
        authDatabase.authUserDao()
}

private const val AuthDatabaseName = "deepfine_auth.db"
