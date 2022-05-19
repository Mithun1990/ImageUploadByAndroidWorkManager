package com.naim.imageuploadbyandroidworkmanager.di

import android.content.Context
import androidx.work.WorkManager
import com.google.gson.Gson
import com.naim.imageuploadbyandroidworkmanager.repository.DummyRepository
import com.naim.imageuploadbyandroidworkmanager.repository.DummyRepositoryImpl
import com.naim.imageuploadbyandroidworkmanager.workers.factory.ImageUploadWorkerFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideAppContext(@ApplicationContext context: Context): Context = context

    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideDummyRepository(): DummyRepository = DummyRepositoryImpl()

    @Provides
    fun provideImageUploadWorkerFactory(gson: Gson, dummyRepository: DummyRepository) =
        ImageUploadWorkerFactory(gson, dummyRepository)

    @Provides
    fun provideWorkManager(@ApplicationContext context: Context): WorkManager =
        WorkManager.getInstance(context)
}