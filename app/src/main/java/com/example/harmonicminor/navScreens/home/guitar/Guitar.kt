package com.example.harmonicminor.navScreens.home.guitar

import com.example.harmonicminor.navScreens.favourite.FavouriteItem
import com.example.harmonicminor.navScreens.search.Searchable
import com.example.harmonicminor.navScreens.shopping.CartItem

data class Guitar(
    override val name: String,
    val type: String,
    override val description: Map<String, String> = emptyMap(),
    val manufacturer: String,
    override val price: String,
    val stock: String,
    val isAvailable: Boolean,
    val imageUrl: String,
    override val thumbnailUrl: String,
    override val id: String = ""
) : Searchable, FavouriteItem, CartItem {
    constructor() : this(
        "", "", emptyMap(), "", "",
        "", true, "", "", ""
    )
}
