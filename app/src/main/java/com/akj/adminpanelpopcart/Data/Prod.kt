package com.akj.adminpanelpopcart.Data

data class Prod(
    val id: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val description: String = "",
    val colors: List<Any> = listOf(),
    val sizes: List<String> = emptyList(), // Make sure this is a List<String>
    val images: List<String> = emptyList(),
    val quantity: Int = 0
)
