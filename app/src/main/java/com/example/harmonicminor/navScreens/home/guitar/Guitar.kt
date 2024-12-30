package com.example.harmonicminor.navScreens.home.guitar

data class Guitar(
    val name: String,
    val type: String,
    val description: String,
    val manufacturer: String,
    val price: String,
    val stock: String,
    val isAvailable: Boolean,
    val imageUrl: String,
    val thumbnailUrl: String,
    var id: String = ""
){
    constructor() : this("", "", "", "", "", "", true, "", "", "")
}
