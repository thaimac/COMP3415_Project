package com.example.piebuilder

import androidx.lifecycle.ViewModel

private const val TAG = "AppViewModel"

class AppViewModel : ViewModel() {
    var currIndex = 0
    private val questionBank = listOf(
            Question(R.string.question_age),
            Question(R.string.question_income),
            Question(R.string.question_net_worth),
            Question(R.string.question_objectives),
            Question(R.string.question_time_horizon),
            Question(R.string.question_investing_knowledge),
            Question(R.string.question_volatility),
            Question(R.string.question_portfolio_growth_decline)
    )

    val currentQuestionText: Int
        get() = questionBank[currIndex].textResId

    fun moveToNext() {
        currIndex++
    }
}