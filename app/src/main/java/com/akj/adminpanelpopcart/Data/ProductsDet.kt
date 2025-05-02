package com.akj.adminpanelpopcart.Data

import android.os.Parcelable

data class ProductsDet(
    val id: String,
    val name: String,
    val category: String,
    val price: Float,
    val offerPercentage: Float? = null,
    val description: String? = null,
    val colors: List<Int>? = null,
    val sizes: List<String>? = null,
    val images: List<String>
){
    constructor():this("0","","",0f,images= emptyList())
}
