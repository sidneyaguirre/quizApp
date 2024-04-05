package com.example.quizapp

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizQuestionsActivity : AppCompatActivity(), OnClickListener {
    private var correctAnswers: Int = 0
    private var currentPosition: Int = 0
    private var flagImage: ImageView? = null
    private var optionOne: TextView? = null
    private var optionTwo: TextView? = null
    private var optionThree: TextView? = null
    private var optionFour: TextView? = null
    private var progressBar: ProgressBar? = null
    private var progressText: TextView? = null
    private var question: TextView? = null
    private var questionList: ArrayList<Question>? = null
    private var selectedOption: Int = 0
    private var submitButton: Button? = null
    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz_questions)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        username = intent.getStringExtra(Constants.USERNAME)

        flagImage = findViewById(R.id.iv_flag_image)
        optionOne = findViewById(R.id.tv_option_one)
        optionTwo = findViewById(R.id.tv_option_two)
        optionThree = findViewById(R.id.tv_option_three)
        optionFour = findViewById(R.id.tv_option_four)
        progressBar = findViewById(R.id.pb_progress_bar)
        progressText = findViewById(R.id.tv_progress)
        question = findViewById(R.id.tv_question)
        submitButton = findViewById(R.id.btn_submit)

        optionOne?.setOnClickListener(this)
        optionTwo?.setOnClickListener(this)
        optionThree?.setOnClickListener(this)
        optionFour?.setOnClickListener(this)
        submitButton?.setOnClickListener(this)

        questionList = Constants.getQuestions()

        setQuestion()

        defaultOptionsView()
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                optionOne?.setTextColor(getColor(R.color.white))
                optionOne?.background = ContextCompat.getDrawable(this, drawableView)
            }

            2 -> {
                optionTwo?.setTextColor(getColor(R.color.white))
                optionTwo?.background = ContextCompat.getDrawable(this, drawableView)
            }

            3 -> {
                optionThree?.setTextColor(getColor(R.color.white))
                optionThree?.background = ContextCompat.getDrawable(this, drawableView)
            }

            4 -> {
                optionFour?.setTextColor(getColor(R.color.white))
                optionFour?.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()

        optionOne?.let { options.add(0, it) }
        optionTwo?.let { options.add(1, it) }
        optionThree?.let { options.add(2, it) }
        optionFour?.let { options.add(3, it) }

        for (option in options) {
            option.setTextColor(getColor(R.color.pink))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this, R.drawable.option_border
            )
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_option_one -> {
                optionOne?.let { selectedOptionView(it, 1) }
            }

            R.id.tv_option_two -> {
                optionTwo?.let { selectedOptionView(it, 2) }
            }

            R.id.tv_option_three -> {
                optionThree?.let { selectedOptionView(it, 3) }
            }

            R.id.tv_option_four -> {
                optionFour?.let { selectedOptionView(it, 4) }
            }

            R.id.btn_submit -> {
                if (selectedOption == 0) {
                    currentPosition++

                    when {
                        currentPosition < questionList!!.size -> setQuestion()
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)

                            intent.putExtra(Constants.USERNAME, username)
                            intent.putExtra(Constants.CORRECT_ANSWERS, correctAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, questionList!!.size)

                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val questionInd = questionList?.get(currentPosition)

                    if (questionInd!!.correctAnswer != selectedOption) {
                        answerView(selectedOption, R.drawable.wrong_option_bg)
                    } else {
                        correctAnswers++
                    }

                    answerView(questionInd.correctAnswer, R.drawable.correct_option_bg)

                    if (currentPosition == questionList!!.size - 1) {
                        submitButton?.text = getString(R.string.finish)
                    } else {
                        submitButton?.text = getString(R.string.next)
                    }

                    selectedOption = 0
                }
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionPos: Int) {
        defaultOptionsView()

        selectedOption = selectedOptionPos

        tv.setTextColor(getColor(R.color.deepPink))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this, R.drawable.selected_option_border
        )
    }

    private fun setQuestion() {
        defaultOptionsView()
        val questionItem = questionList!!.elementAt(currentPosition)

        flagImage?.setImageResource(questionItem.image)
        progressBar?.progress = currentPosition + 1
        progressText?.text = "${currentPosition + 1}/${questionList!!.size}"
        question?.text = questionItem.question
        optionOne?.text = questionItem.optionOne
        optionTwo?.text = questionItem.optionTwo
        optionThree?.text = questionItem.optionThree
        optionFour?.text = questionItem.optionFour

        if (currentPosition != questionList!!.size - 1) {
            submitButton?.text = getString(R.string.submit)
        }
    }
}