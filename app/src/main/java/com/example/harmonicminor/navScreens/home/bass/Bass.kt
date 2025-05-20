package com.example.harmonicminor.navScreens.home.bass

import com.example.harmonicminor.navScreens.favourite.FavouriteItem
import com.example.harmonicminor.navScreens.search.Searchable
import com.example.harmonicminor.navScreens.shopping.CartItem
import com.google.firebase.firestore.PropertyName

data class Bass(
    override val name: String,
    val type: String,
    override val description: Map<String, String> = emptyMap(),
    val manufacturer: String,
    override val price: String,
    val stock: String,
    val isAvailable: Boolean,
    val imageUrl: String,
    @get:PropertyName("thumbNailUrl")
    @set:PropertyName("thumbNailUrl")
    override var thumbnailUrl: String,
    override val id: String
) : Searchable, FavouriteItem, CartItem {
    constructor() : this(
        "", "", emptyMap(), "", "",
        "", true, "", "", ""
    )
}