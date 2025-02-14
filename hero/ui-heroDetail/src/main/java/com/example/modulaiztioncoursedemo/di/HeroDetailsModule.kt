package com.example.modulaiztioncoursedemo.di

import com.example.core.Logger
import com.example.hero_interactors.GetHeroFromCache
import com.example.hero_interactors.GetHeroes
import com.example.hero_interactors.HeroInteractors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeroDetailsModule {
    @Provides
    @Singleton
    fun provideGetHeroFromCache(
        interactors: HeroInteractors,
    ): GetHeroFromCache {
        return interactors.getHeroFromCache

    }

    @Provides
    @Singleton
    @Named("heroDetailsLogger")
    fun provideLogger(): Logger {
        return Logger(
            tag = "HeroDetails",
            isDebug = true
        )

    }
}