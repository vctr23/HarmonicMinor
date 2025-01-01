package com.example.harmonicminor.navScreens.home.microphones

import com.example.harmonicminor.navScreens.search.Searchable

data class Microphone(
    override val name: String,
    val type: String,
    val description: String,
    val manufacturer: String,
    val price: String,
    val stock: String,
    val isAvailable: Boolean,
    val imageUrl: String,
    val thumbnailUrl: String,
    val id: String
) : Searchable {
    constructor() : this(
        "", "", "", "", "",
        "", true, "", "", ""
    )
}
