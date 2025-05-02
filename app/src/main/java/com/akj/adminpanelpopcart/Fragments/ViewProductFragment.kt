package com.akj.adminpanelpopcart.Fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akj.adminpanelpopcart.Adapter.ProductAdapter
import com.akj.adminpanelpopcart.Data.Prod
import com.akj.adminpanelpopcart.R
import com.google.firebase.firestore.FirebaseFirestore

class ViewProductFragment : Fragment(R.layout.fragment_view_product) {
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewProducts)
        productAdapter = ProductAdapter(listOf())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = productAdapter

        loadProducts()
    }

    private fun loadProducts() {
        FirebaseFirestore.getInstance().collection("Products")
            .get()
            .addOnSuccessListener { result ->
                val products = result.map { it.toObject(Prod::class.java) }
                productAdapter.updateProducts(products)
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Error loading products", Toast.LENGTH_SHORT).show()
            }
    }
}