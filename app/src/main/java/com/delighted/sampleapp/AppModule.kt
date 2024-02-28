package com.delighted.sampleapp

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class AppModule {
    @Binds
    abstract fun bindsAppConfig(config: DelightedSampleAppConfig): AppConfig
}
