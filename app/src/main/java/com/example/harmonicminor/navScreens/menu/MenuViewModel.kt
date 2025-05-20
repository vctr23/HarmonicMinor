package com.example.harmonicminor.navScreens.menu

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MenuViewModel: ViewModel (){
    private val _selectedCurrency = MutableStateFlow("euro")
    val selectedCurrency: StateFlow<String> get() = _selectedCurrency

    private val _selectedCountry = MutableStateFlow("es")
    val selectedCountry: StateFlow<String> get() = _selectedCountry

    fun updateCurrency(currency: String) {
        _selectedCurrency.value = currency
    }

    fun updateCountry(country: String) {
        _selectedCountry.value = country
    }
}