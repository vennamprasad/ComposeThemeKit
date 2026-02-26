package prasad.vennam.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import prasad.vennam.datastore.dataStore
import javax.inject.Named
import javax.inject.Singleton
import prasad.vennam.model.ThemeConfig

@Module
@InstallIn(SingletonComponent::class)
object ThemeKitDataStoreModule {

    @Provides
    @Singleton
    @Named("ThemeDataStore")
    fun provideThemeDataStore(@ApplicationContext context: Context): DataStore<ThemeConfig> {
        return context.dataStore
    }
}
