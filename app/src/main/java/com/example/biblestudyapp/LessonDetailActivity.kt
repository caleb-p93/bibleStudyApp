package com.example.biblestudyapp

import androidx.appcompat.app.AppCompatActivity // Import AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LessonDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_detail)

        val title = intent.getStringExtra("lesson_title")
        val content = intent.getStringExtra("lesson_content")

        val titleTextView: TextView = findViewById(R.id.lesson_detail_title)
        val contentTextView: TextView = findViewById(R.id.lesson_detail_content)

        titleTextView.text = title
        contentTextView.text = content
    }
}