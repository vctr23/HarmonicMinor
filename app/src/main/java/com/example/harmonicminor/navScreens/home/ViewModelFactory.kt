package com.example.harmonicminor.navScreens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harmonicminor.navScreens.home.software.ItemViewModel

class ViewModelFactory<T : Identifiable>(private val itemClass: Class<T>) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ItemViewModel(itemClass) as T
    }
}
