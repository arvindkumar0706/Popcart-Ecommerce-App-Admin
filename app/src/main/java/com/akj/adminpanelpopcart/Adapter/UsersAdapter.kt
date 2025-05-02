package com.akj.adminpanelpopcart.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akj.adminpanelpopcart.Data.User
import com.akj.adminpanelpopcart.R

class UsersAdapter(private var users: List<User>) :
    RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val firstNameTextView: TextView = itemView.findViewById(R.id.firstNameTextView)
        val lastNameTextView: TextView = itemView.findViewById(R.id.lastNameTextView)
        val emailTextView: TextView = itemView.findViewById(R.id.emailTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.firstNameTextView.text = "First Name :- ${user.firstname}"
        holder.lastNameTextView.text = "Last Name :- ${user.lastname}"
        holder.emailTextView.text = "Email Address :- ${user.email}"
    }

    override fun getItemCount(): Int = users.size

    fun updateUsers(newUsers: List<User>) {
        users = newUsers
        notifyDataSetChanged()
    }
}