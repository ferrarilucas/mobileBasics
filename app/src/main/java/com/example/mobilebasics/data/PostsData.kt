package com.example.mobilebasics.data

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.mobilebasics.model.Post

class PostsData (val context:Context): ArrayList<Post>(){
    val postList: ArrayList<Post> = ArrayList()
    val requestQueue: RequestQueue = Volley.newRequestQueue(context)
    init {
        getPosts()
    }
    fun getPosts(){
        val apiUrl = "https://jsonplaceholder.typicode.com/posts"
        val request = JsonArrayRequest(
            Request.Method.GET, apiUrl, null,
            { response ->
                for (item in 0 until response.length()) {
                    val jsonPost = response.getJSONObject(item)
                    println("Post: $jsonPost")

                    val Post = Post(
                        jsonPost.getInt("userId"),
                        jsonPost.getInt("id"),
                        jsonPost.getString("title"),
                        jsonPost.getString("body")
                    )
                    postList.add(Post)
                }
            },
            { error ->
                println("Error: $error")
            }
        )
        requestQueue.add(request)
    }

    fun getPostByUserId(id: Int): Post? {
        return postList.find { it.userId == id }
    }

    fun getPostById(id: Int): Post? {
        return postList.find { it.id == id }
    }

    fun getPostsByUserId(id: Int): List<Post> {
        return postList.filter { it.userId == id }
    }

    fun getPostsByTitle(title: String): List<Post> {
        return postList.filter { it.title.contains(title, ignoreCase = true) }
    }

}