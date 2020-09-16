package com.example.madlevel2task2

data class QuestionModel(
    var questionName: String,
    var questionBoolean: Boolean
) {
    companion object {
        val QUESTION_NAMES = arrayOf(
            "Werkt de app al? (true)",
            "Heb ik de google sheet ingevuld? (true)",
            "Mag ik nu naar bed? (false)"
        )
        val QUESTION_STATUS = booleanArrayOf(
            true,
            true,
            false
        )
    }
}