package prasad.vennam.data.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import prasad.vennam.data.AppIconRepositoryImpl
import prasad.vennam.data.ThemeRepositoryImpl
import prasad.vennam.domain.AppIconRepository
import prasad.vennam.domain.ThemeRepository
import prasad.vennam.model.registry.ThemeRegistry
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ThemeKitDataModule {

    @Binds
    @Singleton
    abstract fun bindThemeRepository(
        themeRepositoryImpl: ThemeRepositoryImpl
    ): ThemeRepository

    companion object {
        @Provides
        @Singleton
        fun provideThemeRegistry(): ThemeRegistry {
            return ThemeRegistry()
        }

        @Provides
        @Singleton
        fun provideAppIconRepository(
            @ApplicationContext context: Context,
            themeRegistry: ThemeRegistry
        ): AppIconRepository {
            return AppIconRepositoryImpl(context, themeRegistry)
        }
    }
}
