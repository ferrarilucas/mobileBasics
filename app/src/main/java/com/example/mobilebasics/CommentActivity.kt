package com.example.mobilebasics

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilebasics.data.CommentData
import com.example.mobilebasics.databinding.ActivityCommentBinding
import com.example.mobilebasics.model.Post
class CommentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCommentBinding
    private var postTitle: TextView? = null
    private var postBody: TextView? = null
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewComment.layoutManager = LinearLayoutManager(this)
        val intentRecebida = intent
        val post:Post = intentRecebida.getSerializableExtra("Post", Post::class.java) as Post
        postTitle = findViewById(R.id.post_title)
        postTitle?.text = post.title
        postBody = findViewById(R.id.post_body)
        postBody?.text = post.body

        CommentData(this, post, binding)


        setContentView(R.layout.activity_comment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}