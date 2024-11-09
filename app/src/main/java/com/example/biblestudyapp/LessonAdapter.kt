// LessonAdapter.kt
package com.example.biblestudyapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LessonAdapter(
    private val lessons: List<Lesson>,
    private val clickListener: (Lesson) -> Unit
) : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lesson, parent, false)
        return LessonViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lesson = lessons[position]
        holder.titleTextView.text = lesson.title
        holder.categoryTextView.text = lesson.category

        // Set the click listener for the button
        holder.lessonButton.setOnClickListener {
            clickListener(lesson)
        }
    }

    override fun getItemCount(): Int = lessons.size

    inner class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.title_text_view)
        val categoryTextView: TextView = itemView.findViewById(R.id.category_text_view)
        val lessonButton: Button = itemView.findViewById(R.id.lesson_button) // Button to view lesson
    }
}