package com.example.mobilebasics

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobilebasics.adapter.UserAdapter
import com.example.mobilebasics.data.UsersData
import com.example.mobilebasics.databinding.ActivityMainBinding
import kotlinx.coroutines.*
class MainActivity : AppCompatActivity() {
    private val apiUrl = "https://jsonplaceholder.typicode.com/users"
    private lateinit var binding: ActivityMainBinding
    private val job = Job()
    private val scope = CoroutineScope(Dispatchers.Main + job)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recyclerViewMain.layoutManager = LinearLayoutManager(this)
        val userList = UsersData(this, binding)


            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }



    }

}