package com.example.biblestudyapp

data class Lesson(
    val id: String,
    val title: String,
    val category: String? = null,
    val content: String
)