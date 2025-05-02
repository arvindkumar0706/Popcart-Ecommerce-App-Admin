package com.akj.adminpanelpopcart.Data



data class Order(
    val orderId: Long = 0L,
    val address: Address=Address(),
    val date: String = "",
    val orderStatus: String = "",
    val products: List<Products> = listOf(),
    val totalPrice: Double = 0.0
)