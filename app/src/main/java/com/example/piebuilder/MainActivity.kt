package com.example.piebuilder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import org.w3c.dom.Text

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var input: EditText
    private lateinit var multiple_choice: ListView
    private lateinit var submitInput: Button
    private lateinit var submitInputMultChoice: Button
    private lateinit var questionViewMultChoice: TextView
    private lateinit var show_results: Button
    private lateinit var recommendation: TextView
    private lateinit var specific_holdings: TextView
    private var i = 0
    private var age = 0
    private var income = 0
    private var netWorth = 0
    private var objective = ""
    private var horizon = ""
    private var knowledge = ""
    private var volatility = ""
    private var ideal_portfolio = ""

    private val appViewModel: AppViewModel by lazy{
        ViewModelProviders.of(this).get(AppViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currIndex = savedInstanceState?.getInt(KEY_INDEX,0) ?: 0
        appViewModel.currIndex = currIndex

        questionTextView = findViewById(R.id.question_text_view)
        input = findViewById(R.id.user_input)
        submitInput = findViewById(R.id.submit_input)

        submitInput.setOnClickListener { view: View ->
            Log.i("input submitted", "input submitted $i")
            when (i) {
                0 -> age = Integer.parseInt(input.getText().toString())
                1 -> income = Integer.parseInt(input.getText().toString())
                2 -> {
                    netWorth = Integer.parseInt(input.getText().toString())
                    //val user = User(age, income, netWorth)
                    //Log.i("User info: ", user.toString())
                    multipleChoice()
                }

            }
            i++
            input.setText("")
            appViewModel.moveToNext()
            updateQuestion()

        }
    }

    private fun multipleChoice() {
        setContentView(R.layout.multiple_choice)

        submitInputMultChoice = findViewById(R.id.submit_input2)
        questionViewMultChoice = findViewById(R.id.question_text_view2)

        val multChoice: Array<Array<String>> = arrayOf(
            arrayOf(
                "Preserve capital",
                "Have a steady stream of income",
                "Achieve balanced growth with a moderate level of risk",
                "Achieve better than average asset growth",
                "Maximum asset growth"
            ),
            arrayOf(
                "Less than 1 year",
                "1-4 years",
                "5-9 years",
                "10-14 years",
                "15+ years"
            ),
            arrayOf(
                "Novice",
                "Fairly familiar",
                "Comfortable",
                "Fairly knowledgeable",
                "Expert"
            ),
            arrayOf(
                "That's it, I'm selling everything",
                "It doesn't feel great, but I'm okay",
                "It's all good",
                "I expect fluctuations",
                "Time to buy"
            ),
            arrayOf(
                "Avoid losses while accepting lower returns. Best case 3.7%. Average 2.26%. Worst case: -3%",
                "Keep risk low while seeking modest returns. Best case 5.6%. Average 3.86%. Worst case: -4.8%",
                "Seek medium returns while taking on some risk. Best case 7.8%. Average 6.19%. Worst case: -7%",
                "Seek greater returns while taking on more risk. Best case 12.1%. Average 7.47%. Worst case: -10.8%",
                "Maximize returns while accepting large account value fluctuation. Best case 17.2%. Average 10.05%. Worst case: -15.6%"
            )
        )

        multiple_choice = findViewById(R.id.user_input_multiple_choice)
        multiple_choice.choiceMode = ListView.CHOICE_MODE_SINGLE
        var adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, multChoice[0])
        multiple_choice.adapter = adapter
        multiple_choice.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                objective = parent.getItemAtPosition(position).toString()
                Log.i("Item selected", "$objective")
                appViewModel.moveToNext()
                updateQuestionMultChoice()
                adapter =
                    ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, multChoice[1])
                multiple_choice.adapter = adapter
                multiple_choice.onItemClickListener =
                    AdapterView.OnItemClickListener { parent, view, position, id ->
                        horizon = parent.getItemAtPosition(position).toString()
                        Log.i("Item selected", "$horizon")
                        appViewModel.moveToNext()
                        updateQuestionMultChoice()
                        adapter = ArrayAdapter(
                            this,
                            android.R.layout.simple_list_item_activated_1,
                            multChoice[2]
                        )
                        multiple_choice.adapter = adapter
                        multiple_choice.onItemClickListener =
                            AdapterView.OnItemClickListener { parent, view, position, id ->
                                knowledge = parent.getItemAtPosition(position).toString()
                                Log.i("Item selected", "$knowledge")
                                appViewModel.moveToNext()
                                updateQuestionMultChoice()
                                adapter = ArrayAdapter(
                                    this,
                                    android.R.layout.simple_list_item_activated_1,
                                    multChoice[3]
                                )
                                multiple_choice.adapter = adapter
                                multiple_choice.onItemClickListener =
                                    AdapterView.OnItemClickListener { parent, view, position, id ->
                                        volatility = parent.getItemAtPosition(position).toString()
                                        Log.i("Item selected", "$volatility")
                                        appViewModel.moveToNext()
                                        updateQuestionMultChoice()
                                        adapter = ArrayAdapter(
                                            this,
                                            android.R.layout.simple_list_item_activated_1,
                                            multChoice[4]
                                        )
                                        multiple_choice.adapter = adapter
                                        multiple_choice.onItemClickListener =
                                            AdapterView.OnItemClickListener { parent, view, position, id ->
                                                ideal_portfolio = parent.getItemAtPosition(position).toString()
                                                Log.i("Item selected", "$ideal_portfolio")
                                                val user = User(age, income, netWorth, objective, horizon, knowledge, volatility, ideal_portfolio)
                                                Log.i("User's profile", user.toString())

                                                setContentView(R.layout.get_results)
                                                show_results = findViewById(R.id.show_results)
                                                show_results.setOnClickListener { view: View ->
                                                    Log.i("Results", "Shown here")
                                                    showResults(user)
                                                }
                                            }
                                    }
                            }
                    }
            }
    }

    private fun updateQuestionMultChoice(){
        val questionTextResId = appViewModel.currentQuestionText
        questionViewMultChoice.setText(questionTextResId)
    }

    private fun updateQuestion(){
        val questionTextResId = appViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun showResults(user: User) {
        setContentView(R.layout.show_results)
        recommendation = findViewById(R.id.asset_alloc)
        specific_holdings = findViewById(R.id.specific_holdings)

        if(user.objective == "Preserve capital" || user.horizon == "Less than 1 year" || user.volatility == "That's it, I'm selling everything"
               || user.ideal_portfolio == "Avoid losses while accepting lower returns. Best case 3.7%. Average 2.26%. Worst case: -3%") {
            recommendation.setText(R.string.low_risk_profile)
            specific_holdings.setText(R.string.low_risk_holdings)
        } else if(user.objective == "Achieve balanced growth with a moderate level of risk" || user.volatility == "It doesn't feel great, but I'm okay" ||
                user.ideal_portfolio == "Seek medium returns while taking on some risk. Best case 7.8%. Average 6.19%. Worst case: -7%"){
            recommendation.setText(R.string.medium_risk_profile)
            specific_holdings.setText(R.string.medium_risk_holdings)
        } else if(user.objective == "Achieve better than average asset growth" || user.objective == "Maximum asset growth" || user.volatility == "Time to buy" ||
                 user.volatility == "I expect fluctuations") {
            recommendation.setText(R.string.high_risk_profile)
            specific_holdings.setText(R.string.high_risk_holdings)
        }
    }
}