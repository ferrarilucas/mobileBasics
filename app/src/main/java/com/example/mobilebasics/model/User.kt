package com.example.mobilebasics.model

data class User (
    val id: Int,
    val name: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
)
