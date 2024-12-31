package com.example.harmonicminor.navScreens.home.software

import com.example.harmonicminor.navScreens.home.Identifiable

data class Software(
    val name: String,
    val type: String,
    val description: String,
    val manufacturer: String,
    val price: String,
    val stock: String,
    val isAvailable: Boolean,
    val imageUrl: String,
    val thumbnailUrl: String,
    override val id: String
) : Identifiable {
    constructor() : this("", "", "", "", "", "", true, "", "", "")
}
