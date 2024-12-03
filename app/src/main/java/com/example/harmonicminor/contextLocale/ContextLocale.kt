package com.example.harmonicminor.contextLocale

import android.content.Context
import android.content.res.Configuration
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.staticCompositionLocalOf
import java.util.Locale

class LanguageManager(context: Context) {
    private val appContext = context.applicationContext
    val currentLocale = mutableStateOf(Locale("es")) // Default to Spanish

    fun updateLanguage(language: String) {
        val newLocale = Locale(language)
        if (currentLocale.value != newLocale) {
            currentLocale.value = newLocale
            appContext.updateLocale(newLocale)
        }
    }
}

// Update Locale in the application context
fun Context.updateLocale(locale: Locale) {
    val configuration = Configuration(resources.configuration)
    configuration.setLocale(locale)
    resources.updateConfiguration(configuration, resources.displayMetrics)
}

// CompositionLocal for providing the LanguageManager
val LocalLanguageManager = staticCompositionLocalOf<LanguageManager> {
    error("No LanguageManager provided")
}
