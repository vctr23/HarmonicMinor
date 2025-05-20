package com.example.harmonicminor.navScreens.favourite

interface FavouriteItem {
    val id: String
    val name: String
    val description: Map<String, String>
    val thumbnailUrl: String
}