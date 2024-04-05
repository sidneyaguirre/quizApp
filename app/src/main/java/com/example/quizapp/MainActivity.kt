package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btStart: Button = findViewById(R.id.btStart)
        val etName: EditText = findViewById(R.id.tiNameInput)

        btStart.setOnClickListener {
            if (etName.text.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please enter your name",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val intent = Intent(this, QuizQuestionsActivity::class.java)

                //Pass Data to other Activity
                intent.putExtra(Constants.USERNAME, etName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}