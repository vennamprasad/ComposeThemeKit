package prasad.vennam.design

import prasad.vennam.model.registry.ThemeRegistry

/**
 * The main entry point for the AuraComposeThemeKit library.
 * 
 * AuraComposeThemeKit is designed as a "Theming-as-a-Service" implementation. Instead of rigid enums,
 * it provides a dynamic engine where colors, fonts, and interaction styles can be 
 * registered at runtime.
 */
object AuraComposeThemeKit {
    /**
     * Configures the AuraComposeThemeKit engine. 
     * 
     * Use this in your `Application` class or main Activity to register custom
     * branding assets (colors, fonts, profiles) into the [ThemeRegistry].
     *
     * @param block A configuration block scoped to [ThemeRegistry].
     */
    fun configure(block: ThemeRegistry.() -> Unit) {
        ThemeRegistry.block()
    }
}
