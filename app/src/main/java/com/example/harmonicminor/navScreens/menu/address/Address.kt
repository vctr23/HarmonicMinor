package com.example.harmonicminor.navScreens.menu.address

data class Address(
    var id: String = "",
    var name: String = "",
    var lastname: String = "",
    var street: String = "",
    var postcode: String = "",
    var locality: String = "",
    var country: String = "",
    var phone: String = ""
){
    constructor() : this("", "", "", "", "", "", "", "")
}
