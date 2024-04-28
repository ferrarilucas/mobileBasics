package com.example.mobilebasics.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilebasics.CommentsActivity
import com.example.mobilebasics.R
import com.example.mobilebasics.data.UsersData
import com.example.mobilebasics.model.Post
import com.example.mobilebasics.model.User
import com.example.mobilebasics.postsActivity

class PostsAdapter(val postList: ArrayList<Post>, val userName: String, val context:Context) : RecyclerView.Adapter<PostsAdapter.UserViewHolder>()  {
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val post_card: CardView = itemView.findViewById(R.id.card_view)
        val title: TextView = itemView.findViewById(R.id.post_title)
        val body: TextView = itemView.findViewById(R.id.post_body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.posts_cards, parent, false)

        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val post = postList[position]
        holder.title.setText(post.title)
        holder.body.setText(post.body)
        holder.post_card.setOnClickListener {
            val intent = Intent(context, CommentsActivity::class.java)
            intent.putExtra("PostId", post.id);

            startActivity(context, intent, null);
            println("Post: $post")
        }
    }
}