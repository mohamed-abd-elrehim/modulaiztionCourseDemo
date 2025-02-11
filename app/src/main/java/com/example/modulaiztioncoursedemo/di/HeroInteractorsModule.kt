package com.example.modulaiztioncoursedemo.di

import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.hero_interactors.HeroInteractors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HeroInteractorsModule {

    @Provides
    @Singleton
    @Named("heroAndroidSqlDriver") // in case you had another SQL Delight db
    fun provideAndroidDriver(app: Application): SqlDriver {
        return AndroidSqliteDriver(
            HeroInteractors.schema,
            app,
            HeroInteractors.dbName
        )
    }

    @Provides
    @Singleton
    fun provideHeroIntractors(
        @Named("heroAndroidSqlDriver") sqlDriver:SqlDriver,
    ):HeroInteractors{
        return HeroInteractors.build(sqlDriver)
    }



    
}