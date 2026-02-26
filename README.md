# 🎨 AuraComposeThemeKit: Modern Theming as a Service
[![](https://jitpack.io/v/vennamprasad/AuraComposeThemeKit.svg)](https://jitpack.io/#vennamprasad/AuraComposeThemeKit)
![Build Version](https://img.shields.io/badge/version-1.0.0-blue.svg)
![Jetpack Compose](https://img.shields.io/badge/Jetpack-Compose-4285F4.svg?logo=jetpackcompose)

**AuraComposeThemeKit** is a professional-grade, modular theming engine for Android Jetpack Compose. Unlike traditional static themes, AuraComposeThemeKit acts as a pluggable service that gives you total control over **Colors**, **Typography**, **Geometry**, **Haptics**, and **Animations** with zero boilerplate.

---

## 🚀 Key Features

- 🛠 **Dynamic Registry**: Register custom fonts, brand colors, and icon styles at runtime.
- 🎭 **Curated Profiles**: One-tap "Theme Profiles" (like *Cyberpunk*, *Nordic Forest*, *Retro Terminal*) that transform the entire app's look and feel.
- ⚡ **Auto-Scaling Components**: A suite of library components (`ThemeButton`, `ThemeCard`, `ThemeTextField`) that automatically respond to your theme's geometry and spacing rules.
- 📳 **Haptic Engine**: Built-in vibration feedback that scales with the selected "Haptic Intensity."
- 📊 **Themed Data Viz**: High-performance `ThemeLineChart` and `ThemeProgressCircle` that pull colors directly from your brand palette.
- 🌑 **True Black & High Contrast**: Native support for OLED-friendly dark modes and accessibility-first high-contrast elevations.

---

## 📦 Installation

**1. Add the JitPack repository to your root `settings.gradle.kts`:**

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

**2. Add the dependency in your app's `build.gradle.kts`:**

```kotlin
dependencies {
    // The Whole Kit: Includes Design System, Models, and Settings UI
    implementation("com.github.vennamprasad:ComposeThemeKit:1.0.0")
}
```

> [!TIP]
> **Modular Installation**: If you only need the specific building blocks without the pre-built Settings UI:
> - **Core UI & Engine**: `implementation("com.github.vennamprasad:ComposeThemeKit-core-designsystem:1.0.0")`
> - **Models Only**: `implementation("com.github.vennamprasad:ComposeThemeKit-core-model:1.0.0")`

---

## 🛠 Usage

### 1. Configure the Registry
Inject your custom branding assets on application startup:

```kotlin
AuraComposeThemeKit.configure {
    // Add a signature brand color
    colors.add(ThemeColor(id = "neon", name = "Cyber Neon", colorValue = 0xFF00FF00))
    
    // Register a custom font family
    fonts.add(ThemeFont(id = "inter", name = "Inter", fontRes = R.font.inter))
    
    // Create a curated custom skin
    profiles.add(ThemeProfile(
        id = "stealth",
        name = "Stealth Mode",
        config = ThemeConfig(isDarkTheme = true, isTrueBlack = true, brandColorId = "neon")
    ))
}
```

### 2. Use Theme-Aware Components
Place components in your UI that listen to the global `ThemeConfig` automatically:

```kotlin
@Composable
fun MyScreen() {
    Column {
        ThemeButton(onClick = { /* Haptics and shapes applied automatically! */ }) {
            Text("Launch App")
        }
        
        ThemeLineChart(dataPoints = listOf(10f, 50f, 30f)) // Colors match your branding
    }
}
```

---

## 🧩 Modular Architecture

AuraComposeThemeKit is built for large-scale production apps with a strictly decoupled architecture:

| Module | Description |
| :--- | :--- |
| `core:designsystem` | The main UI component library and Theme engine. |
| `core:model` | Logic-free data models and the `ThemeRegistry`. |
| `core:datastore` | Automated persistence for user theme preferences. |
| `feature:settings` | A full, ready-to-use settings screen with live previews. |

---

## 📄 License
AuraComposeThemeKit is available under the MIT License. See [LICENSE](LICENSE) for more details.

---
*Built with ❤️ for performance-oriented Android teams.*
