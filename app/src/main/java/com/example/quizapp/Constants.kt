package com.example.quizapp

object Constants {
    const val CORRECT_ANSWERS: String = "correct_answers"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val USERNAME: String = "username"

    private val questions = arrayOf(
        Question(
            1,
            question = "What country does this flag belong to?",
            image = R.drawable.argentina,
            "Argentina",
            "Australia",
            "Armenia",
            "Austria",
            correctAnswer = 1
        ),
        Question(
            2,
            question = "What country does this flag belong to?",
            image = R.drawable.brazil,
            "Peru",
            "Rwanda",
            "Brazil",
            "Philippines",
            correctAnswer = 3
        ),
        Question(
            3,
            question = "What country does this flag belong to?",
            image = R.drawable.russia,
            "Paraguay",
            "Russia",
            "Portugal",
            "Samoa",
            correctAnswer = 2
        ),
        Question(
            4,
            question = "What country does this flag belong to?",
            image = R.drawable.south_korea,
            "Venezuela",
            "Cuba",
            "Belarus",
            "South Korea",
            correctAnswer = 4
        )
    )

    fun getQuestions(): ArrayList<Question> {
        val questionList = ArrayList<Question>()

        questionList.addAll(questions)

        return questionList
    }
}