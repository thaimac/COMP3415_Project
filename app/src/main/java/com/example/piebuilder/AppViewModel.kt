package com.example.piebuilder

import androidx.lifecycle.ViewModel

private const val TAG = "AppViewModel"

class AppViewModel : ViewModel() {
    var currIndex = 0
    private val questionBank = listOf(
            Question(R.string.question_age),
            Question(R.string.question_height),
            Question(R.string.question_weight),
            Question(R.string.question_target_weight),
            Question(R.string.question_workout_length),
            Question(R.string.question_workout_frequency),
            Question(R.string.question_fitness_level),
            Question(R.string.question_build_goal),
            Question(R.string.question_workout_area),
            Question(R.string.question_weights),
            Question(R.string.question_gym)
    )

    val currentQuestionText: Int
        get() = questionBank[currIndex].textResId

    val questionOne: Int
        get() = questionBank[0].textResId

    fun moveToNext() {
        currIndex++
    }
}