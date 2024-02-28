package com.delighted.sdk

import android.content.Context
import com.delighted.sdk.core.AppDispatchersImpl
import com.delighted.sdk.repository.SurveyFlowRepository
import com.delighted.sdk.repository.SurveyFlowRepositoryImpl
import com.delighted.sdk.repository.SurveyInitRepository
import com.delighted.sdk.repository.SurveyInitRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DelightedModule {
    @Binds
    @Singleton
    abstract fun providesSurveyInitRepository(surveyInitRepositoryImpl: SurveyInitRepositoryImpl): SurveyInitRepository

    @Binds
    @Singleton
    abstract fun providesSurveyFlowRepository(surveyFlowRepositoryImpl: SurveyFlowRepositoryImpl): SurveyFlowRepository

    @Binds
    abstract fun bindDispatchers(impl: AppDispatchersImpl): AppDispatchers

    companion object {
        @Provides
        fun provideLocalCache(@ApplicationContext context: Context): LocalCache {
            val sharedPreferences =
                context.getSharedPreferences(
                    LocalCache.LOCAL_CACHE_KEY,
                    Context.MODE_PRIVATE
                )
            return LocalCache(sharedPreferences = sharedPreferences)
        }
    }
}
