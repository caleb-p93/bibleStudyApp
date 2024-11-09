// MainActivity.kt
package com.example.biblestudyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var lessonAdapter: LessonAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.lesson_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        lessonAdapter = LessonAdapter(lessons) { lesson ->
            // Handle click event
            val intent = Intent(this, LessonDetailActivity::class.java).apply {
                putExtra("lesson_title", lesson.title)
                putExtra("lesson_content", lesson.content)
            }
            startActivity(intent)
        }

        recyclerView.adapter = lessonAdapter

    }
}