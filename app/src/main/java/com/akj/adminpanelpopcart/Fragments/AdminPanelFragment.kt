package com.akj.adminpanelpopcart.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.akj.adminpanelpopcart.R

class AdminPanelFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_admin_panel, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btnAddProduct).setOnClickListener {
            findNavController().navigate(R.id.action_adminPanelFragment_to_addProductFragment)
        }

        view.findViewById<Button>(R.id.btnViewProducts).setOnClickListener {
            findNavController().navigate(R.id.action_adminPanelFragment_to_viewProductsFragment)
        }



        view.findViewById<Button>(R.id.btnViewUsers).setOnClickListener {
            findNavController().navigate(R.id.action_adminPanelFragment_to_viewUsersFragment)
        }

        view.findViewById<Button>(R.id.btnOrder).setOnClickListener {
            findNavController().navigate(R.id.action_adminPanelFragment_to_allOrdersFragment)
        }





    }
}