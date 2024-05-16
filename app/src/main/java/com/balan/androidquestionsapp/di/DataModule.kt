package com.balan.androidquestionsapp.di

import android.content.Context
import androidx.room.Room
import com.balan.androidquestionsapp.data.AuthRepositoryImpl
import com.balan.androidquestionsapp.data.ResultRepositoryImpl
import com.balan.androidquestionsapp.data.ScoreRepositoryImpl
import com.balan.androidquestionsapp.data.TestRepositoryImpl
import com.balan.androidquestionsapp.data.UserSessionImpl
import com.balan.androidquestionsapp.domain.database.AppDataBase
import com.balan.androidquestionsapp.domain.database.UserDao
import com.balan.androidquestionsapp.domain.database.UserLocalSource
import com.balan.androidquestionsapp.domain.database.UserLocalSourceImpl
import com.balan.androidquestionsapp.domain.repository.AuthRepository
import com.balan.androidquestionsapp.domain.repository.ResultRepository
import com.balan.androidquestionsapp.domain.repository.ScoreRepository
import com.balan.androidquestionsapp.domain.repository.TestRepository
import com.balan.androidquestionsapp.domain.user.UserSession
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideAuthRepository(userLocalSource: UserLocalSource): AuthRepository {
        return AuthRepositoryImpl(userLocalSource)
    }

    @Provides
    @Singleton
    fun provideTestRepository(): TestRepository {
        return TestRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideResultRepository(): ResultRepository {
        return ResultRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideUserSession(): UserSession {
        return UserSessionImpl()
    }

    @Provides
    @Singleton
    fun provideScoreRepository(): ScoreRepository {
        return ScoreRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideUserLocalSource(userDao: UserDao): UserLocalSource { //Room
        return UserLocalSourceImpl(userDao)
    }

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext applicationContext: Context): AppDataBase { //Room
        return Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "database.db"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDataBase: AppDataBase): UserDao { //Room
        return appDataBase.getUserDao()
    }
}