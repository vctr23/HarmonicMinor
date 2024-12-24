package com.example.harmonicminor.navScreens.menu

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MenuViewModel: ViewModel (){
    private val _selectedCurrency = MutableStateFlow("Euro")
    val selectedCurrency: StateFlow<String> get() = _selectedCurrency

    private val _selectedCountry = MutableStateFlow("España")
    val selectedCountry: StateFlow<String> get() = _selectedCountry

    private val _selectedLanguage = MutableStateFlow("es")
    val selectedLanguage: StateFlow<String> = _selectedLanguage

    fun updateLanguage(language: String) {
        _selectedLanguage.value = language
    }

    fun updateCurrency(currency: String) {
        _selectedCurrency.value = currency
    }

    fun updateCountry(country: String) {
        _selectedCountry.value = country
    }
}