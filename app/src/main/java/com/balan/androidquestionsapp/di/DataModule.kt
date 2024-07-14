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
import com.balan.androidquestionsapp.data.UserValidatorImpl
import com.balan.androidquestionsapp.domain.database.AppDataBase
import com.balan.androidquestionsapp.domain.database.dao.UserDao
import com.balan.androidquestionsapp.domain.repository.AssetManager
import com.balan.androidquestionsapp.domain.repository.AuthRepository
import com.balan.androidquestionsapp.domain.repository.ResultRepository
import com.balan.androidquestionsapp.domain.repository.ScoreRepository
import com.balan.androidquestionsapp.domain.repository.TestRepository
import com.balan.androidquestionsapp.domain.repository.UserLocalSource
import com.balan.androidquestionsapp.domain.repository.UserValidator
import com.balan.androidquestionsapp.domain.usecase.auth.AuthenticateAdminUseCase
import com.balan.androidquestionsapp.domain.usecase.auth.SignInUseCase
import com.balan.androidquestionsapp.domain.usecase.auth.SignUpUseCase
import com.balan.androidquestionsapp.domain.usecase.result.GetQuestionScoreUseCase
import com.balan.androidquestionsapp.domain.usecase.result.SetQuestionSizeUseCase
import com.balan.androidquestionsapp.domain.usecase.score.DeleteResultUseCase
import com.balan.androidquestionsapp.domain.usecase.test.GetQuestionTitleUseCase
import com.balan.androidquestionsapp.domain.usecase.test.GetQuestionsUseCase
import com.balan.androidquestionsapp.domain.usecase.test.UpdateScoreUseCase
import com.balan.androidquestionsapp.domain.usecase.user_session.GetCurrentUserUseCase
import com.balan.androidquestionsapp.domain.usecase.user_session.GetLevelUseCase
import com.balan.androidquestionsapp.domain.usecase.user_session.SetQuestionLevelUseCase
import com.balan.androidquestionsapp.domain.usecase.user_session.SetUserUseCase
import com.balan.androidquestionsapp.domain.usecase.user_session.UpdateUserInfoUseCase
import com.balan.androidquestionsapp.domain.usecase.user_source.GetAllUserUseCase
import com.balan.androidquestionsapp.domain.usecase.user_source.SortByDirectionUseCase
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
    fun provideAuthRepository(
        userLocalSource: UserLocalSource,
        userValidator: UserValidator
    ): AuthRepository {
        return AuthRepositoryImpl(userLocalSource, userValidator)
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
    fun provideUserValidator(userLocalSource: UserLocalSource): UserValidator {
        return UserValidatorImpl(userLocalSource)
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
    fun provideSignInUseCase(
        authRepository: AuthRepository,
        userSession: UserSession
    ): SignInUseCase {
        return SignInUseCase(authRepository, userSession)
    }

    @Provides
    fun provideSignUpUseCase(authRepository: AuthRepository): SignUpUseCase {
        return SignUpUseCase(authRepository)
    }

    @Provides
    fun provideQuestionLevelUseCase(userSession: UserSession): SetQuestionLevelUseCase {
        return SetQuestionLevelUseCase(userSession)
    }

    @Provides
    fun provideGetLevelUseCase(userSession: UserSession): GetLevelUseCase {
        return GetLevelUseCase(userSession)
    }

    @Provides
    fun provideGetCurrentUserUseCase(userSession: UserSession): GetCurrentUserUseCase {
        return GetCurrentUserUseCase(userSession)
    }

    @Provides
    fun provideUpdateInfoUseCase(userSession: UserSession): UpdateUserInfoUseCase {
        return UpdateUserInfoUseCase(userSession)
    }

    @Provides
    fun provideGetQuestionsUseCase(
        testRepository: TestRepository,
        userSession: UserSession
    ): GetQuestionsUseCase {
        return GetQuestionsUseCase(testRepository, userSession)
    }

    @Provides
    fun provideUpdateScoreUseCase(testRepository: TestRepository): UpdateScoreUseCase {
        return UpdateScoreUseCase(testRepository)
    }

    @Provides
    fun provideDeleteResultUseCase(
        scoreRepository: ScoreRepository,
        userSession: UserSession
    ): DeleteResultUseCase {
        return DeleteResultUseCase(scoreRepository, userSession)
    }

    @Provides
    fun provideGetAllUseCase(userLocalSource: UserLocalSource): GetAllUserUseCase {
        return GetAllUserUseCase(userLocalSource)
    }

    @Provides
    fun provideSortByDirectionUseCase(userLocalSource: UserLocalSource): SortByDirectionUseCase {
        return SortByDirectionUseCase(userLocalSource)
    }

    @Provides
    fun provideSetQuestionSizeUseCase(resultRepository: ResultRepository): SetQuestionSizeUseCase {
        return SetQuestionSizeUseCase(resultRepository)
    }


    @Provides
    fun provideGetQuestionScoreUseCase(resultRepository: ResultRepository): GetQuestionScoreUseCase {
        return GetQuestionScoreUseCase(resultRepository)
    }

    @Provides
    fun provideSetUserUseCase(userSession: UserSession): SetUserUseCase {
        return SetUserUseCase(userSession)
    }

    @Provides
    fun provideAdminAccessUseCase(authRepository: AuthRepository): AuthenticateAdminUseCase {
        return AuthenticateAdminUseCase(authRepository)
    }

    @Provides
    fun provideGetQuestionTitleUseCase(
        testRepository: TestRepository,
        userSession: UserSession
    ): GetQuestionTitleUseCase {
        return GetQuestionTitleUseCase(testRepository, userSession)
    }
}
