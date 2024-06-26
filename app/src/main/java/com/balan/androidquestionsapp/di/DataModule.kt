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
import com.balan.androidquestionsapp.domain.usecase.auth.SignInUseCase
import com.balan.androidquestionsapp.domain.usecase.auth.SignUpUseCase
import com.balan.androidquestionsapp.domain.usecase.score.DeleteResultUseCase
import com.balan.androidquestionsapp.domain.usecase.test.GetQuestionsUseCase
import com.balan.androidquestionsapp.domain.usecase.test.UpdateScoreUseCase
import com.balan.androidquestionsapp.domain.usecase.user_session.GetCurrentUserUseCase
import com.balan.androidquestionsapp.domain.usecase.user_session.GetLevelUseCase
import com.balan.androidquestionsapp.domain.usecase.user_session.QuestionLevelUseCase
import com.balan.androidquestionsapp.domain.usecase.user_session.UpdateInfoUseCase
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

    @Provides
    @Singleton
    fun provideSignInUseCase(authRepository: AuthRepository): SignInUseCase {
        return SignInUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(authRepository: AuthRepository): SignUpUseCase {
        return SignUpUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideQuestionLevelUseCase(userSession: UserSession): QuestionLevelUseCase {
        return QuestionLevelUseCase(userSession)
    }

    @Provides
    @Singleton
    fun provideGetLevelUseCase(userSession: UserSession): GetLevelUseCase {
        return GetLevelUseCase(userSession)
    }

    @Provides
    @Singleton
    fun provideGetCurrentUserUseCase(userSession: UserSession): GetCurrentUserUseCase {
        return GetCurrentUserUseCase(userSession)
    }

    @Provides
    @Singleton
    fun provideUpdateInfoUseCase(userSession: UserSession): UpdateInfoUseCase {
        return UpdateInfoUseCase(userSession)
    }

    @Provides
    @Singleton
    fun provideGetQuestionsUseCase(testRepository: TestRepository): GetQuestionsUseCase {
        return GetQuestionsUseCase(testRepository)
    }

    @Provides
    @Singleton
    fun provideUpdateScoreUseCase(testRepository: TestRepository): UpdateScoreUseCase {
        return UpdateScoreUseCase(testRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteResultUseCase(scoreRepository: ScoreRepository) : DeleteResultUseCase{
        return DeleteResultUseCase(scoreRepository)
    }
}