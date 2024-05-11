package com.balan.androidquestionsapp.di

import com.balan.androidquestionsapp.data.AuthRepositoryImpl
import com.balan.androidquestionsapp.data.ResultRepositoryImpl
import com.balan.androidquestionsapp.data.ScoreRepositoryImpl
import com.balan.androidquestionsapp.data.TestRepositoryImpl
import com.balan.androidquestionsapp.data.UserSessionImpl
import com.balan.androidquestionsapp.domain.repository.AuthRepository
import com.balan.androidquestionsapp.domain.repository.ResultRepository
import com.balan.androidquestionsapp.domain.repository.ScoreRepository
import com.balan.androidquestionsapp.domain.repository.TestRepository
import com.balan.androidquestionsapp.domain.user.UserSession
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideAuthRepository(): AuthRepository {
        return AuthRepositoryImpl()
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
}