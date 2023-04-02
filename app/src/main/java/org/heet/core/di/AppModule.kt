package org.heet.core.di

import android.app.Application
import android.util.DisplayMetrics
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.heet.data.dispatcher.StandardDispatcherProvider
import org.heet.domain.interfaces.DispatcherInterface
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideDisplayMetrics(app: Application): DisplayMetrics = app.resources.displayMetrics

    @Singleton
    @Provides
    fun provideDispatcherProvider(): DispatcherInterface {
        return StandardDispatcherProvider()
    }
}
