// MainActivity.kt
package com.example.biblestudyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent
import android.widget.AdapterView
import android.widget.SearchView
import android.widget.Spinner
import android.widget.ArrayAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var lessonAdapter: LessonAdapter
    private lateinit var searchView: SearchView
    private lateinit var categorySpinner: Spinner

    private val categories = listOf(
        "All",
        "Important Aspects of a Christ Follower",
        "Who God Is",
        "What God Does",
        "Heaven, Hell and Sin",
        "Living Like Christ",
        "Being Human",
        "People of the Bible"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.lesson_recycler_view)
        searchView = findViewById(R.id.search_view)
        categorySpinner = findViewById(R.id.category_spinner)

        recyclerView.layoutManager = LinearLayoutManager(this)

        lessonAdapter = LessonAdapter(LessonData.lessons) { lesson ->
            // Handle click event
            val intent = Intent(this, LessonDetailActivity::class.java).apply {
                putExtra("lesson_title", lesson.title)
                putExtra("lesson_category", lesson.category)
                putExtra("lesson_content", lesson.content)
            }
            startActivity(intent)
        }

        recyclerView.adapter = lessonAdapter

        // Set up the category spinner
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                val selectedCategory = categories[position]
                filterLessons(selectedCategory)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        // Set up the search view
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                lessonAdapter.filter.filter(newText)
                return true
            }
        })
    }

    private fun filterLessons(category: String) {
        val filteredLessons = if (category == "All") {
            LessonData.lessons
        } else {
            LessonData.lessons.filter { it.category == category }
        }
        lessonAdapter.updateLessons(filteredLessons)
    }
}