package demo.sahha.android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Named("mainScope")
    @Singleton
    @Provides
    fun provideMainScope(): CoroutineScope {
        return CoroutineScope(Dispatchers.Main)
    }

    @Named("defaultScope")
    @Singleton
    @Provides
    fun provideDefaultScope(): CoroutineScope {
        return CoroutineScope(Dispatchers.Default)
    }

    @Named("ioScope")
    @Singleton
    @Provides
    fun provideIoScope(): CoroutineScope {
        return CoroutineScope(Dispatchers.IO)
    }
}