package demo.sahha.android.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import demo.sahha.android.data.repo.AnalyzeRepoImpl
import demo.sahha.android.data.repo.AuthRepoImpl
import demo.sahha.android.data.repo.ProfileRepoImpl
import demo.sahha.android.domain.repo.AnalyzeRepo
import demo.sahha.android.domain.repo.AuthRepo
import demo.sahha.android.domain.repo.ProfileRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton


private const val file_name: String = "auth_data"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Named("mainScope")
    @Provides
    fun provideMainScope(): CoroutineScope {
        return CoroutineScope(Dispatchers.Main)
    }

    @Named("defaultScope")
    @Provides
    fun provideDefaultScope(): CoroutineScope {
        return CoroutineScope(Dispatchers.Default)
    }

    @Named("ioScope")
    @Provides
    fun provideIoScope(): CoroutineScope {
        return CoroutineScope(Dispatchers.IO)
    }

    @Provides
    @Singleton
    fun provideAuthRepo(
        sharedPreferences: SharedPreferences
    ): AuthRepo {
        return AuthRepoImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideAnalyzeRepo(

    ): AnalyzeRepo {
        return AnalyzeRepoImpl()
    }

    @Provides
    @Singleton
    fun provideProfileRepo(): ProfileRepo {
        return ProfileRepoImpl()
    }

    @Provides
    @Singleton
    fun provideEncryptedSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            context,
            file_name,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}