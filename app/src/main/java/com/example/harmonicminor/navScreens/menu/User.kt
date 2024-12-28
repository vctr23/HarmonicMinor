package com.example.harmonicminor.navScreens.menu


data class User(
    val username: String = "",
    val email: String = ""
) {
    constructor() : this("", "")
}


