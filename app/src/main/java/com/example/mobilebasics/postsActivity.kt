package com.example.mobilebasics

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilebasics.data.PostsData
import com.example.mobilebasics.databinding.ActivityPostsBinding
import kotlinx.coroutines.*
class postsActivity : AppCompatActivity() {

        private lateinit var binding: ActivityPostsBinding
        private val job = Job()
        private val scope = CoroutineScope(Dispatchers.Main + job)
        private var userTitle:TextView? = null
        @SuppressLint("SuspiciousIndentation")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            binding = ActivityPostsBinding.inflate(layoutInflater)
            setContentView(binding.root)


            binding.recyclerViewPost.layoutManager = LinearLayoutManager(this)
            val intentRecebida = intent
            val userId = intentRecebida.getIntExtra("userId", 0)
            val userName = intentRecebida.getStringExtra("userName").toString()
            userTitle = findViewById(R.id.user_title)
            userTitle?.text = "Posts de $userName:"
            val userList = PostsData(this, userId, userName, binding)


            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }



        }


}