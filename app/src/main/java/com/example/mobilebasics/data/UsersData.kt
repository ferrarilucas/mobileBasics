package com.example.mobilebasics.data

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.mobilebasics.adapter.UserAdapter
import com.example.mobilebasics.databinding.ActivityMainBinding
import com.example.mobilebasics.model.Address
import com.example.mobilebasics.model.Company
import com.example.mobilebasics.model.Geo
import com.example.mobilebasics.model.User
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.suspendCoroutine

class UsersData(val context:Context, val binding: ActivityMainBinding) : ArrayList<User>() {
    val userList: ArrayList<User> = ArrayList()
    val requestQueue: RequestQueue = Volley.newRequestQueue(context)
    init {
        getUsers()
    }
     fun getUsers(){

        val apiUrl = "https://jsonplaceholder.typicode.com/users"

            val request = JsonArrayRequest(
                Request.Method.GET, apiUrl, null,
                { response ->
                    for (item in 0 until response.length()) {
                        val jsonUser = response.getJSONObject(item)
                        println("User: $jsonUser")

                        val User = User(
                            jsonUser.getInt("id"),
                            jsonUser.getString("name"),
                            jsonUser.getString("email"),
                            Address(
                                jsonUser.getJSONObject("address").getString("street"),
                                jsonUser.getJSONObject("address").getString("suite"),
                                jsonUser.getJSONObject("address").getString("city"),
                                jsonUser.getJSONObject("address").getString("zipcode"),
                                Geo(
                                    jsonUser.getJSONObject("address").getJSONObject("geo")
                                        .getString("lat"),
                                    jsonUser.getJSONObject("address").getJSONObject("geo")
                                        .getString("lng")
                                )
                            ),
                            jsonUser.getString("phone"),
                            jsonUser.getString("website"),
                            Company(
                                jsonUser.getJSONObject("company").getString("name"),
                                jsonUser.getJSONObject("company").getString("catchPhrase"),
                                jsonUser.getJSONObject("company").getString("bs")
                            )
                        )
                        userList.add(User)
                    }
                    println("User List: $userList")
                    binding.recyclerViewMain.adapter = UserAdapter(userList, context)
                },
                { error: VolleyError? ->
                    println("Error: $error")
                })

            requestQueue.add(request)

    }
    fun getUserById(id: Int): User? {
        return this.find { it.id == id }
    }

    fun getUserByName(name: String): User? {
        return this.find { it.name == name }
    }

    fun getUserByEmail(email: String): User? {
        return this.find { it.email == email }
    }
}