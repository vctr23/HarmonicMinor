package com.example.harmonicminor.navScreens.home

data class Category(
    val name: String,
    val imageRes: Int,
    val onClick: () -> Unit
)
