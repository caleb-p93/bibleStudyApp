// LessonAdapter.kt
package com.example.biblestudyapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class LessonAdapter(
    private var lessons: List<Lesson>,
    private val clickListener: (Lesson) -> Unit
) : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>(), Filterable {

    private var lessonsFull: List<Lesson> = ArrayList(lessons) // Full list for filtering

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lesson, parent, false)
        return LessonViewHolder(view)
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val lesson = lessons[position]
        holder.lessonNumberTextView.text = lesson.id // Display lesson number
        holder.titleTextView.text = lesson.title
        holder.categoryTextView.text = lesson.category

        // Set the click listener for the button
        holder.lessonButton.setOnClickListener {
            clickListener(lesson)
        }
    }

    override fun getItemCount(): Int = lessons.size

    inner class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lessonNumberTextView: TextView = itemView.findViewById(R.id.lesson_number_text_view)
        val titleTextView: TextView = itemView.findViewById(R.id.title_text_view)
        val categoryTextView: TextView = itemView.findViewById(R.id.category_text_view)
        val lessonButton: Button = itemView.findViewById(R.id.lesson_button) // Button to view lesson
    }

    // Filterable implementation
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList = if (constraint.isNullOrEmpty()) {
                    lessonsFull
                } else {
                    val filterPattern = constraint.toString().lowercase(Locale.ROOT).trim()
                    lessonsFull.filter {
                        it.title.lowercase(Locale.ROOT).contains(filterPattern) ||
                                it.category.lowercase(Locale.ROOT).contains(filterPattern)
                    }
                }

                val results = FilterResults()
                results.values = filteredList
                return results
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                lessons = results?.values as List<Lesson>
                notifyDataSetChanged()
            }
        }
    }

    // Method to update the lessons list
    fun updateLessons(newLessons: List<Lesson>) {
        lessons = newLessons
        lessonsFull = ArrayList(newLessons) // Update the full list for filtering
        notifyDataSetChanged()
    }
}