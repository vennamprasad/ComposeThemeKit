package prasad.vennam.data

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import prasad.vennam.domain.AppIconRepository
import prasad.vennam.model.registry.ThemeRegistry
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppIconRepositoryImpl @Inject constructor(
    private val context: Context
) : AppIconRepository {
    override fun setAppIconId(id: String) {
        val packageManager = context.packageManager
        val packageName = context.packageName
        
        val targetIcon = ThemeRegistry.appIcons.find { it.id == id } ?: return

        // Disable all other icons
        ThemeRegistry.appIcons.forEach { appIcon ->
            if (appIcon.id != targetIcon.id) {
                packageManager.setComponentEnabledSetting(
                    ComponentName(packageName, "$packageName.${appIcon.aliasName}"),
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP
                )
            }
        }

        // Enable selected icon
        packageManager.setComponentEnabledSetting(
            ComponentName(packageName, "$packageName.${targetIcon.aliasName}"),
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )
    }
}
