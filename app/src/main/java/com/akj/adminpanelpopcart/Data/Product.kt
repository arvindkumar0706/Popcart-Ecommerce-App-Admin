package com.akj.adminpanelpopcart.Data

data class Products(
    val id: String,
    val name: String,
    val price: Float,
    val description: String? = null,
    val colors: String ,
    val sizes: String,
    val images: List<String>,
){

    constructor() : this("", "",  0.0f, null, "", "", emptyList())
}

