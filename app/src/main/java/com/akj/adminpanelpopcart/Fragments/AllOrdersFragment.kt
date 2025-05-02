package com.akj.adminpanelpopcart.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akj.adminpanelpopcart.Adapter.OrdersAdapter
import com.akj.adminpanelpopcart.Data.Order
import com.akj.adminpanelpopcart.Data.Products
import com.akj.adminpanelpopcart.Data.Quantity
import com.akj.adminpanelpopcart.R
import com.google.firebase.firestore.FirebaseFirestore

class AllOrdersFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var ordersAdapter: OrdersAdapter
    private lateinit var ordersList: ArrayList<Order>
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_all_orders, container, false)

        recyclerView = view.findViewById(R.id.ordersRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        ordersList = arrayListOf()

        fetchOrders()

        return view
    }

    private fun fetchOrders() {
        db.collection("orders")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val order = document.toObject(Order::class.java)

                    // Retrieve the products field, which is a list of maps with numeric keys
                    val productsList = document["products"] as? List<Map<String, Any>> ?: emptyList()

                    // Map each product using the numeric keys
                    val products = productsList.flatMap { productMap ->
                        productMap.mapNotNull { (key, value) -> // Use mapNotNull to filter out null values
                            val productDetails = value as? Map<String, Any> ?: return@mapNotNull null // Safely retrieve product details

                            // Check if essential product fields are not empty or null before creating a Products object
                            val id = productDetails["id"] as? String ?: return@mapNotNull null
                            val name = productDetails["name"] as? String ?: return@mapNotNull null
                            val price = (productDetails["price"] as? Number)?.toFloat() ?: return@mapNotNull null

                            Products(
                                id = id,
                                name = name,
                                price = price,
                                description = productDetails["description"] as? String,
                                colors = (productDetails["colors"] as? List<Number>)?.joinToString(",") ?: "",
                                sizes = (productDetails["sizes"] as? List<String>)?.joinToString(",") ?: "",
                                images = productDetails["images"] as? List<String> ?: listOf()
                            )
                        }
                    }

                    // Create a new order with the filtered products
                    val newOrder = order.copy(products = products)
                    ordersList.add(newOrder)
                }

                // Assuming ordersAdapter is your RecyclerView adapter
                ordersAdapter = OrdersAdapter(ordersList)
                recyclerView.adapter = ordersAdapter
            }
            .addOnFailureListener { exception ->
                Log.w("AllOrdersFragment", "Error getting documents: ", exception)
            }
    }




}
