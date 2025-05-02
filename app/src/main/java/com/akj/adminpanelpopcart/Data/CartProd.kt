package com.akj.adminpanelpopcart.Data

data class CartProd(
    val product:Products,
    val quantity:Int,
    val selectedColor:Int?=null,
    val selectedSize: String? =null
)
