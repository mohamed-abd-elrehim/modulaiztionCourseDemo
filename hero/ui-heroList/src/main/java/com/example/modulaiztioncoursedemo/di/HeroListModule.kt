package com.example.modulaiztioncoursedemo.di

import com.example.core.Logger
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
object HeroListModule {
    @Provides
    @Singleton
    fun provideGetHeroes(
        interactors: HeroInteractors,
    ): GetHeroes{
        return interactors.getHeroes

    }
    @Provides
    @Singleton
    @Named("heroListLogger")
    fun provideLogger(): Logger {
        return Logger(
            tag = "HeroList",
            isDebug = true
        )

    }

}