package com.akj.adminpanelpopcart.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.akj.adminpanelpopcart.Adapter.UsersAdapter
import com.akj.adminpanelpopcart.Data.User
import com.akj.adminpanelpopcart.R
import com.akj.adminpanelpopcart.databinding.FragmentViewUserBinding
import com.google.firebase.firestore.FirebaseFirestore

class ViewUsersFragment : Fragment() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var userAdapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewUserBinding.inflate(inflater, container, false)

        firestore = FirebaseFirestore.getInstance()

        // Initialize RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        userAdapter = UsersAdapter(emptyList())
        binding.recyclerView.adapter = userAdapter

        // Fetch Users from Firestore
        fetchUsers()

        return binding.root
    }

    private fun fetchUsers() {
        firestore.collection("user")
            .get()
            .addOnSuccessListener { result ->
                val users = result.documents.mapNotNull { doc ->
                    doc.toObject(User::class.java)
                }
                userAdapter.updateUsers(users)
            }
            .addOnFailureListener {
                // Handle error
                Log.e("UserFragment", "Error fetching users", it)
            }
    }
}
