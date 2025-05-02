package com.akj.adminpanelpopcart.Data

data class Address(
    val addressTitle:String="",
    val fname:String="",
    val street: String = "",
    val city: String = "",
    val state: String = "",
    val phone:String=""
) {
    constructor() : this("", "", "", "","")
}

