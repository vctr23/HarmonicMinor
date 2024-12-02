package com.example.harmonicminor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import com.example.harmonicminor.contextLocale.LanguageManager
import com.example.harmonicminor.contextLocale.LocalLanguageManager
import com.example.harmonicminor.navigation.MyappNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val languageManager = LanguageManager(applicationContext)
        setContent {
            CompositionLocalProvider(LocalLanguageManager provides languageManager) {
                MyappNavigation()
            }
        }
    }
}
