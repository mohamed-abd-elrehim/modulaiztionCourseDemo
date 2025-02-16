package com.example.modulaiztioncoursedemo.di

import com.example.core.domain.Queue
import com.example.core.domain.UIComponent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ErrorQueueModule {

    @Provides
    @Singleton
    fun provideUIComponent(): UIComponent {
        return UIComponent.Dialog(
            title = "",
            description = ""
        )
    }

    @Provides
    @Singleton
    fun provideUIComponentList(): MutableList<UIComponent> {
        return mutableListOf() // Provide an empty mutable list
    }
    @Provides
    @Singleton
    fun provideErrorQueue(list: MutableList<UIComponent>): Queue<UIComponent> {
        return Queue(list) // Pass the provided list into the Queue
    }

}


