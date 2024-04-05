package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    private var correctAnswers: Int? = null
    private var totalQuestions: Int? = null
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        username = intent.getStringExtra(Constants.USERNAME)

        val tvUsername: TextView = findViewById(R.id.tv_username)
        val tvScore: TextView = findViewById(R.id.tv_score)
        val finishButton: Button = findViewById(R.id.btn_finish_quiz)

        tvUsername.text = username
        tvScore.text = "Your Score is $correctAnswers out of $totalQuestions"

        finishButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
