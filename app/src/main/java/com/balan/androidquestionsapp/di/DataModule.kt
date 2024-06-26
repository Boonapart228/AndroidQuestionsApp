package com.balan.androidquestionsapp.di

import android.content.Context
import androidx.room.Room
import com.balan.androidquestionsapp.data.AssetManagerImpl
import com.balan.androidquestionsapp.data.AuthRepositoryImpl
import com.balan.androidquestionsapp.data.ResultRepositoryImpl
import com.balan.androidquestionsapp.data.ScoreRepositoryImpl
import com.balan.androidquestionsapp.data.TestRepositoryImpl
import com.balan.androidquestionsapp.data.UserLocalSourceImpl
import com.balan.androidquestionsapp.data.UserSessionImpl
import com.balan.androidquestionsapp.domain.database.AppDataBase
import com.balan.androidquestionsapp.domain.database.dao.UserDao
import com.balan.androidquestionsapp.domain.repository.AssetManager
import com.balan.androidquestionsapp.domain.repository.AuthRepository
import com.balan.androidquestionsapp.domain.repository.ResultRepository
import com.balan.androidquestionsapp.domain.repository.ScoreRepository
import com.balan.androidquestionsapp.domain.repository.TestRepository
import com.balan.androidquestionsapp.domain.repository.UserLocalSource
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
    fun provideTestRepository(assetManager: AssetManager): TestRepository {
        return TestRepositoryImpl(assetManager)
    }

    @Provides
    @Singleton
    fun provideResultRepository(assetManager: AssetManager): ResultRepository {
        return ResultRepositoryImpl(assetManager)
    }

    @Provides
    @Singleton
    fun provideUserSession(userLocalSource: UserLocalSource): UserSession {
        return UserSessionImpl(userLocalSource)
    }

    @Provides
    @Singleton
    fun provideScoreRepository(userLocalSource: UserLocalSource): ScoreRepository {
        return ScoreRepositoryImpl(userLocalSource)
    }

    @Provides
    @Singleton
    fun provideUserLocalSource(userDao: UserDao): UserLocalSource {
        return UserLocalSourceImpl(userDao)
    }

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext applicationContext: Context): AppDataBase {
        return Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "database.db"
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDataBase: AppDataBase): UserDao {
        return appDataBase.getUserDao()
    }

    @Provides
    @Singleton
    fun provideAssetManager(@ApplicationContext context: Context): AssetManager {
        return AssetManagerImpl(context)
    }
}