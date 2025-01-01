package com.example.harmonicminor.navScreens.home.bass

import com.example.harmonicminor.navScreens.search.Searchable

data class Bass(
    override val name: String,
    val type: String,
    val description: String,
    val manufacturer: String,
    val price: String,
    val stock: String,
    val isAvailable: Boolean,
    val imageUrl: String,
    val thumbNailUrl: String,
    val id: String
) : Searchable {
    constructor() : this(
        "", "", "", "", "",
        "", true, "", "", ""
    )
}
