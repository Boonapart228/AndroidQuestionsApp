package com.balan.androidquestionsapp.di

import com.balan.androidquestionsapp.data.AuthorizationRepositoryImpl
import com.balan.androidquestionsapp.domain.repository.AuthorizationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthorizationModule {

    @Provides
    @Singleton
    fun provideAuthorizationRepository() : AuthorizationRepository{
        return AuthorizationRepositoryImpl()
    }
}