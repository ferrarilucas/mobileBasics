package com.example.mobilebasics.data

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.mobilebasics.adapter.CommentAdapter
import com.example.mobilebasics.adapter.PostsAdapter
import com.example.mobilebasics.databinding.ActivityCommentBinding
import com.example.mobilebasics.model.Comment
import com.example.mobilebasics.model.Post

class CommentData(val context:Context, val post:Post, val binding:ActivityCommentBinding) {
    val commentList: ArrayList<Comment> = ArrayList()
    val requestQueue: RequestQueue = Volley.newRequestQueue(context)
    init {
        getPosts()
    }
    fun getPosts(){
        val apiUrl = "https://jsonplaceholder.typicode.com/comments?postId=${post.id}"
        val request = JsonArrayRequest(
            Request.Method.GET, apiUrl, null,
            { response ->
                for (item in 0 until response.length()) {
                    val jsonPost = response.getJSONObject(item)
                    println("Post: $jsonPost")

                    val Comment = Comment(
                        jsonPost.getInt("PostId"),
                        jsonPost.getInt("id"),
                        jsonPost.getString("name"),
                        jsonPost.getString("email"),
                        jsonPost.getString("body")
                    )
                    commentList.add(Comment)
                }
                binding.recyclerViewComment.adapter = CommentAdapter(commentList, context)
            },
            { error ->
                println("Error: $error")
            }
        )
        requestQueue.add(request)
    }

}