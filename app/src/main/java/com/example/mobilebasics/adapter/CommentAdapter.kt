package com.example.mobilebasics.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilebasics.R
import com.example.mobilebasics.model.Comment

class CommentAdapter(val commentList: List<Comment>, val context: Context) : RecyclerView.Adapter<CommentAdapter.UserViewHolder>() {
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.comment_name)
        val email: TextView = itemView.findViewById(R.id.comment_email)
        val body: TextView = itemView.findViewById(R.id.comment_body)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.comment, parent, false)

        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val comment = commentList[position]
        holder.title.setText(comment.name)
        holder.email.setText("by: ${comment.email}")
        holder.body.setText(comment.body)
    }
}