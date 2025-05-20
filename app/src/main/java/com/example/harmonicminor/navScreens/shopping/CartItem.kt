package com.example.harmonicminor.navScreens.shopping

interface CartItem {
    val id: String
    val name: String
    val price: String
    val description: Map<String, String>
    val thumbnailUrl: String
}