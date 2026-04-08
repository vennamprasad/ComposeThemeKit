# Compose Theme Kit - Architecture Guide

This document provides a comprehensive overview of the **Theme Kit** architecture, designed to help developers understand, implement, and extend the system.

## 1. High-Level Overview

The Theme Kit is a highly decoupled, modular design system library for Android (Jetpack Compose). It follows **Clean Architecture** principles to separate the theme state, persistence, and UI rendering.

### Core Modules

| Module | Responsibility |
| :--- | :--- |
| `:core:model` | Pure Kotlin data classes (ThemeConfig, ThemeColor, etc.) and the `ThemeRegistry`. |
| `:core:domain` | Use cases and interfaces defining theme management operations (`ThemeRepository`). |
| `:core:data` | Implementation of repository interfaces, handling mapping between storage and models. |
| `:core:datastore` | Persistence layer using **Jetpack DataStore (Proto)** for high-performance theme saving. |
| `:core:designsystem` | The "Heart" of the system. Contains the `ThemeKitTheme` wrapper, Material 3 mapping, custom modifiers, and the Settings UI. |
| `:app` | A sample application demonstrating how to integrate and use the kit. |

---

## 2. The Seven Pillars of Theming

The system centers around seven key aesthetic dimensions, all packed into a single `ThemeConfig` data class:

1. **Colors**: Support for Material 3 Dynamic Colors (Android 12+), static brand colors, and "Color Harmony" (blending the whole UI towards a primary brand color).
2. **Typography**: Real-time text scaling and custom font family support (Nunito, Poppins, Raleway, etc.).
3. **Shapes**: Dynamic corner radius scaling (Square, Rounded, Extra Rounded).
4. **Spacing**: Adaptive Layout Spacing (Extra Small to XXX Large) with a "Compact Mode" toggle.
5. **Motion**: Unified animation scale control (from instant to cinematic slow-motion).
6. **Haptics**: Variable haptic feedback intensity (None, Light, Medium, Heavy) integrated into UI components.
7. **Elevations**: Support for Material 3 shadows, Flat styles, and **Neumorphic** (soft UI) rendering.

---

## 3. Key Components & Implementation

### `ThemeRegistry` (The Plug-and-Play Hub)

Located in `:core:model`. This is where you register what options are available to the user.

* Design Choice: It uses a registry pattern so developers can inject custom branding without touching the library code.
* Implementation: Simply add items to `colors`, `fonts`, or `profiles` before passing it to the provider.

### `ThemeKitProvider` (The Easy Entry)

Located in `:core:designsystem`. A high-level wrapper that:

1. Provides the `SettingsViewModel` via Hilt.
2. Collects theme state from DataStore.
3. Wraps your content in `ThemeKitTheme`.

### `getAppColorScheme` (The Harmony Engine)

Located in `Theme.kt`. This function performs complex ARGB blending.

* Logic: It takes the base Material 3 scheme and "harmonizes" it by blending surfaces and tertiary colors towards the selected brand color (using `androidx.core.graphics.ColorUtils.blendARGB`).

---

## 4. Design Patterns Used

* **MVI/MVVM**: `SettingsViewModel` exposes a single `StateFlow<ThemeConfig>`.
* **Dependency Injection**: Hilt is used for providing the `ThemeRepository` and `DataStore`.
* **CompositionLocal**: We use `CompositionLocalProvider` to make `Dimensions`, `HapticEngine`, and `ThemeRegistry` available anywhere in the Compose tree without prop-drilling.
* **Harmonized Theming**: Rather than just changing the primary color, we blend the entire palette to ensure consistent visual "vibe."

---

## 5. How to Implement it in a New Project

1. **Add Dependencies**: Include the `:core:designsystem` and `:core:model` modules.
2. **Initialize Hilt**: Annotate your Application class with `@HiltAndroidApp`.
3. **Wrap Content**: In your `MainActivity`, use `ThemeKitProvider`.

    ```kotlin
    ThemeKitProvider {
        MainScreen() // Your App UI
    }
    ```

4. **Use Components**: Use `ThemeButton`, `ThemeSnackbar`, and the `themeSettings()` modifier to automatically stay in sync with the user's preferences.

---

## 6. Architectural Benefits

* **Robustness**: If DataStore fails or is empty, it gracefully falls back to `ThemeConfig()` defaults.
* **Performance**: State is collected with `collectAsStateWithLifecycle` to avoid background leaked updates.
* **Scalability**: Adding a new theme property (e.g., "Glassmorphism") only requires adding a field to `ThemeConfig` and a new `CompositionLocal`.

---

## 7. Developer Workflow & Best Practices

To ensure a "Perfect" implementation, follow these architectural best practices:

### 1. Component Theming

Never hardcode colors or dimensions. Always pull from the `MaterialTheme` or our custom `LocalDimensions`.

* BAD: `Modifier.padding(16.dp).background(Color.Blue)`
* GOOD: `Modifier.padding(LocalDimensions.current.spacing.medium).background(MaterialTheme.colorScheme.primary)`

### 2. Haptic Feedback

Use the `LocalHapticEngine` in your custom components. It automatically checks if the user has disabled haptics or set them to "Heavy."

```kotlin
val haptic = LocalHapticEngine.current
Button(onClick = { 
    haptic.performClick()
    // ... logic
})
```

### 3. Themed Icons

Avoid using `Icons.Default.Home`. Instead, use `LocalIcons.current.home`. This allows the user to switch the entire app's icon style (e.g., from *Outlined* to *Filled*) from the settings screen.

### 4. Adaptive Shapes

Always use the theme's shapes, which are automatically scaled by `ThemeConfig.styleShapeScale`.

```kotlin
Surface(shape = MaterialTheme.shapes.medium) { ... }
```

---

## 8. Troubleshooting & FAQ

* **Theme doesn't persist?** Ensure Hilt is initialized and the `ThemeRepository` is correctly bound to `ThemePreferencesDataSource`.
* **Dynamic Color not working?** Dynamic color requires Android 12 (S) or higher. On older versions, the system gracefully falls back to the default or brand color.
* **App Icon doesn't change?** Ensure you have registered the corresponding `activity-alias` in your `AndroidManifest.xml`.
