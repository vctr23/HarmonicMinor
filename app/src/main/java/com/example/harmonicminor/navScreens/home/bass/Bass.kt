package com.example.harmonicminor.navScreens.home.bass

data class Bass(
    val name: String,
    val type: String,
    val description: String,
    val manufacturer: String,
    val price: String,
    val stock: String,
    val isAvailable: Boolean,
    val imageUrl: String,
    val thumbNailUrl: String,
    val id: String
){
    constructor() : this("", "", "", "", "", "", true, "", "", "")
}
