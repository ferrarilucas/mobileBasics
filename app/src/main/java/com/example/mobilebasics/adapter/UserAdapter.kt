package com.example.mobilebasics.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilebasics.MainActivity
import com.example.mobilebasics.R
import com.example.mobilebasics.model.User
import com.example.mobilebasics.postsActivity

class UserAdapter(val userList: ArrayList<User>, val context:Context) : RecyclerView.Adapter<UserAdapter.UserViewHolder>()  {
        class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val TextView: Button = itemView.findViewById(R.id.user_button)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_button, parent, false)

        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.TextView.setText(user.name)
        holder.TextView.setOnClickListener {
            val intent = Intent(context, postsActivity::class.java)
            intent.putExtra("userId", user.id);
            startActivity(context, intent, null);
            println("User: $user")
        }

    }
}