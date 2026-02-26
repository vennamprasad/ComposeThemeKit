package prasad.vennam.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import prasad.vennam.model.ThemeConfig

val Context.dataStore: DataStore<ThemeConfig> by dataStore(
    fileName = "theme_config.json",
    serializer = ThemeConfigSerializer
)
