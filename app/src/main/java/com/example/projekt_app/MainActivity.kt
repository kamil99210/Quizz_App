package com.example.projekt_app

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val questionCountSpinner: Spinner = findViewById(R.id.spinner_question_count)
        val startQuizButton: Button = findViewById(R.id.button_start_quiz)

        // Ustawienie opcji w Spinner
        ArrayAdapter.createFromResource(
            this@MainActivity,
            R.array.question_count_array,
            R.layout.spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            questionCountSpinner.adapter = adapter
        }

        startQuizButton.setOnClickListener {
            val questionCount = questionCountSpinner.selectedItem.toString().toInt()
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("QUESTION_COUNT", questionCount)
            startActivity(intent)
        }
    }
}
