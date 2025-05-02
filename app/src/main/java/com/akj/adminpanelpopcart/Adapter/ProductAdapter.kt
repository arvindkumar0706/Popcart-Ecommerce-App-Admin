package com.akj.adminpanelpopcart.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akj.adminpanelpopcart.Data.Prod
import com.akj.adminpanelpopcart.R
import com.bumptech.glide.Glide

class ProductAdapter(private var products: List<Prod>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productName: TextView = view.findViewById(R.id.productName)
        val productCategory: TextView = view.findViewById(R.id.productCategory)
        val productPrice: TextView = view.findViewById(R.id.productPrice)
        val productOffer: TextView = view.findViewById(R.id.productOffer)
        val productDescription: TextView = view.findViewById(R.id.productDescription)
        val productColors: TextView = view.findViewById(R.id.productColors)
        val productSizes: TextView = view.findViewById(R.id.productSizes)
        val productImage1: ImageView = view.findViewById(R.id.productImage1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]

        // Binding product name
        holder.productName.text = product.name

        // Binding price
        holder.productPrice.text = "Price: $${product.price}"

        // Binding description
        holder.productDescription.text = product.description ?: "No Description Available"

        // Binding colors
        if (!product.colors.isNullOrEmpty()) {
            val colorText = product.colors.map { it.toString() }.joinToString(", ")
            holder.productColors.text = colorText
            holder.productColors.visibility = View.VISIBLE
        } else {
            holder.productColors.visibility = View.GONE
        }

        // Binding sizes
        if (!product.sizes.isNullOrEmpty()) {
            holder.productSizes.text = product.sizes.joinToString(", ")
            holder.productSizes.visibility = View.VISIBLE
        } else {
            holder.productSizes.visibility = View.GONE
        }

        // Binding images using Glide
        if (product.images.isNotEmpty()) {
            Glide.with(holder.itemView.context)
                .load(product.images[0]) // Load the first image
                .placeholder(R.drawable.image_placeholder)
                .into(holder.productImage1)
        } else {
            holder.productImage1.setImageResource(R.drawable.image_placeholder)
        }
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun updateProducts(newProducts: List<Prod>) {
        products = newProducts
        notifyDataSetChanged()
    }
}
