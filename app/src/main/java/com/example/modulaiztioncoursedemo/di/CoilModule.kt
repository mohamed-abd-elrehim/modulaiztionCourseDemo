package com.example.modulaiztioncoursedemo.di

import android.content.Context
import coil.ImageLoader
import coil.disk.DiskCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import coil.memory.MemoryCache
import com.example.modulaiztioncoursedemo.R

@Module
@InstallIn(SingletonComponent::class)
object CoilModule {

    @Provides
    @Singleton // Marks this function as providing a singleton instance of ImageLoader.
    fun provideImageLoader(
        @ApplicationContext context: Context // Injects the application context using Hilt.
    ): ImageLoader {
        return ImageLoader.Builder(context)
            .crossfade(true) // Enables crossfade animation when loading images.
            .diskCache {
                DiskCache.Builder()
                    .directory(context.cacheDir.resolve("image_cache")) // Sets the directory for disk caching.
                    .maxSizeBytes(50L * 1024 * 1024) // Limits the disk cache size to 50 MB.
                    .build()
            }
            .memoryCache {
                MemoryCache.Builder(context)
                    .maxSizePercent(0.25) // Allocates up to 25% of available app memory for caching.
                    .build()
            }
            .okHttpClient {
                OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS) // Sets a 30-second timeout for network requests.
                    .build()
            }
            .error(R.drawable.error_image) // Sets a default error image if loading fails.)
            .build()
    }
}

