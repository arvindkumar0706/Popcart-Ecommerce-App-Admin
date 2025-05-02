package com.akj.adminpanelpopcart.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akj.adminpanelpopcart.Data.Order
import com.akj.adminpanelpopcart.R

class OrdersAdapter(private val ordersList: List<Order>) :
    RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>() {

    class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val orderIdTextView: TextView = view.findViewById(R.id.orderIdTextView)
        val addressTextView: TextView = view.findViewById(R.id.addressTextView)
        val dateTextView: TextView = view.findViewById(R.id.dateTextView)
        val statusTextView: TextView = view.findViewById(R.id.statusTextView)
        val totalPriceTextView: TextView = view.findViewById(R.id.totalPriceTextView)
        val productsTextView: TextView = view.findViewById(R.id.productsTextView)
        val quantity:TextView=view.findViewById(R.id.quantityTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = ordersList[position]
        holder.orderIdTextView.text = "Order ID: ${order.orderId}\n"
        holder.addressTextView.text = "Address : ${order.address.addressTitle}\n${order.address.fname}\n${order.address.phone}\n${order.address.street}\n${order.address.city}\n${order.address.state}\n"
        holder.dateTextView.text = "Date: ${order.date}\n"
        holder.statusTextView.text = "Status: ${order.orderStatus}\n"
        holder.totalPriceTextView.text = "Total Price: ₹${String.format("%.2f",order.totalPrice)} \n"

        val productsText = order.products.joinToString("\n") { "\nName :- ${it.name}\nDescription :- ${it.description} \nImages :- ${it.images} \nColors :-  ${it.colors} \nSizes :- ${it.sizes} " }
        holder.productsTextView.text = productsText
    }

    override fun getItemCount(): Int {
        return ordersList.size
    }
}
