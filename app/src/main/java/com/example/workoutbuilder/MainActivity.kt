package com.example.workoutbuilder

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {
    //XML variable set
    private lateinit var questionTextView: TextView
    private lateinit var input: EditText
    private lateinit var multiple_choice: ListView
    private lateinit var submitInput: Button
    private lateinit var submitInputMultChoice: Button
    private lateinit var questionViewMultChoice: TextView
    private lateinit var show_results: Button
    private lateinit var recommendation: TextView
    private lateinit var workout_plan: TextView

    //Function variable set
    private var i = 0
    private var age = 0
    private var height = 0
    private var weight = 0
    private var targetWeight = 0
    private var length = ""
    private var frequency = ""
    private var fitnessLevelNow = ""
    private var buildGoal = ""
    private var workoutArea = ""
    private var weights = ""
    private var gym = ""

    private var weightDiff = 0  // for finding the difference between current and target weight


    private val appViewModel: AppViewModel by lazy{
        ViewModelProviders.of(this).get(AppViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currIndex = savedInstanceState?.getInt(KEY_INDEX,0) ?: 0
        appViewModel.currIndex = currIndex

        //link XML components to their corresponding variables
        questionTextView = findViewById(R.id.question_text_view)
        input = findViewById(R.id.user_input)
        submitInput = findViewById(R.id.submit_input)

        //on click listener for text input questions
        submitInput.setOnClickListener { view: View ->
            Log.i("input submitted", "input submitted $i")
            when (i) {
                0 -> age = Integer.parseInt(input.getText().toString()) //save user's age
                1 -> height = Integer.parseInt(input.getText().toString()) // save user's height (need to make float)
                2 -> weight = Integer.parseInt(input.getText().toString()) // save user's weight (need to make float)
                3 -> {
                    targetWeight = Integer.parseInt(input.getText().toString()) // save user's target weight (need to make float)
                    multipleChoice() //function call to present multiple choice questions
                }
            }
            i++
            input.setText("")
            appViewModel.moveToNext()
            updateQuestion()

        }
    }

    //Function to display multiple choice questions and save user input
    private fun multipleChoice() {
        setContentView(R.layout.multiple_choice)

        submitInputMultChoice = findViewById(R.id.submit_input2)
        questionViewMultChoice = findViewById(R.id.question_text_view2)

        //two dimensional array to hold options for multiple choice questions
        val multChoice: Array<Array<String>> = arrayOf(
            arrayOf(  // for workout length
                "30 minutes or less",
                "1 hour",
                "More than 1 hour"
            ),
            arrayOf(  // for workout frequency
                //"1 day per week",
                //"2 days per week",
                "3 days per week",
                "4 days per week",
                //"5 days per week",
                "6 days per week"
                //"Every day"
            ),
            arrayOf(  // for fitness level now
                "Do not work out much at all",
                //"Okay (work out once a month or so)",
                //"Average (work out once every few weeks)",
                "Fairly fit (work out once every week, maybe with routines)",
                "Very fit (work out often, have routines, etc.)"
            ),
            arrayOf(  // for build goal
                "Endurance",
                "Hypertrophy",
                "Strength"
            )
            /*arrayOf(  // for workout area
                "Core",
                "Cardio",
                "Flexibility",
                "Lower Body",
                "Upper Body",
                "Full Body"
            ),
            arrayOf(  // for workout with or without weights
                "With Weights",
                "Without Weights"
            ),
            arrayOf(  // for in a gym vs at home
                "In a gym (with machines)",
                "At home (without machines)"
            )*/
        )

        multiple_choice = findViewById(R.id.user_input_multiple_choice)
        multiple_choice.choiceMode = ListView.CHOICE_MODE_SINGLE

        //use an adapter to display the multiple choice options. Iterate over the 2D array to display
        //possible answers to each question
        var adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_list_item_activated_1,
                multChoice[0]
            )
        multiple_choice.adapter = adapter
        multiple_choice.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                length = parent.getItemAtPosition(position).toString()
                appViewModel.moveToNext()
                updateQuestionMultChoice()
                adapter =
                    ArrayAdapter(
                        this,
                        android.R.layout.simple_list_item_activated_1,
                        multChoice[1]
                    )
                multiple_choice.adapter = adapter
                multiple_choice.onItemClickListener =
                    AdapterView.OnItemClickListener { parent, view, position, id ->
                        frequency = parent.getItemAtPosition(position).toString()
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
                                fitnessLevelNow = parent.getItemAtPosition(position).toString()
                                appViewModel.moveToNext()
                                updateQuestionMultChoice()
                                adapter = ArrayAdapter(
                                    this,
                                    android.R.layout.simple_list_item_activated_1,
                                    multChoice[3]
                                )/*
                                multiple_choice.adapter = adapter
                                multiple_choice.onItemClickListener =
                                    AdapterView.OnItemClickListener { parent, view, position, id ->
                                        buildGoal = parent.getItemAtPosition(position).toString()
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
                                                workoutArea = parent.getItemAtPosition(position).toString()
                                                appViewModel.moveToNext()
                                                updateQuestionMultChoice()
                                                adapter = ArrayAdapter(
                                                    this,
                                                    android.R.layout.simple_list_item_activated_1,
                                                    multChoice[5]
                                                )
                                                multiple_choice.adapter = adapter
                                                multiple_choice.onItemClickListener =
                                                    AdapterView.OnItemClickListener { parent, view, position, id ->
                                                        weights = parent.getItemAtPosition(position).toString()
                                                        appViewModel.moveToNext()
                                                        updateQuestionMultChoice()
                                                        adapter = ArrayAdapter(
                                                            this,
                                                            android.R.layout.simple_list_item_activated_1,
                                                            multChoice[6]
                                                        )*/
                                                        multiple_choice.adapter = adapter
                                                        multiple_choice.onItemClickListener =
                                                            AdapterView.OnItemClickListener { parent, view, position, id ->
                                                                //gym = parent.getItemAtPosition(position).toString()
                                                                //create User instance with the user's answers
                                                                val user = User(
                                                                    age,
                                                                    height,
                                                                    weight,
                                                                    targetWeight,
                                                                    length,
                                                                    frequency,
                                                                    fitnessLevelNow,
                                                                    buildGoal
                                                                    /*workoutArea,
                                                                    weights,
                                                                    gym*/
                                                                )

                                                                setContentView(R.layout.get_results)
                                                                show_results =
                                                                    findViewById(R.id.show_results)
                                                                show_results.setOnClickListener { view: View ->
                                                                    showResults(user)
                                                                }
                                                            }
                                                    }
                                            //}
                                   // }
                            //}
                    }
            }
    }

    //function to update multiple choice question
    private fun updateQuestionMultChoice(){
        val questionTextResId = appViewModel.currentQuestionText
        questionViewMultChoice.setText(questionTextResId)
    }

    //function to update regular question
    private fun updateQuestion(){
        val questionTextResId = appViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    //function to provide the user with their personalized workout plan
    private fun showResults(user: User) {
        setContentView(R.layout.show_results)
        recommendation = findViewById(R.id.routine_overview)
        workout_plan = findViewById(R.id.workout_plan)
        assignProfile(user)
    }

    private fun assignProfile(user: User) {
        var points = 0

        //points for age
        if(user.age <12 || user.age > 65) {
            points += 0
        } else
        {
            points +=5
        }

        //points for workout length
        if(user.length == "30 minutes or less"){
            points += 4
        } else if(user.length== "1 hour") {
            points += 8
        } else if(user.length == "More than 1 hour") {
            points += 12
        }

        //points for workout frequency
        if(user.frequency =="3 days per week") {
            points += 3
        } else if(user.frequency=="4 days per week"){
            points += 30
        } else if(user.frequency =="6 days per week"){
            points += 70
        }

        //points for fitness level
        if(user.fitnessLevelNow == "Do not work out much at all") {
            points += 2
        } else if(user.fitnessLevelNow== "Fairly fit (work out once every week, maybe with routines)") {
            points += 4
        } else if(user.fitnessLevelNow == "Very fit (work out often, have routines, etc") {
            points += 6
        }
        //finding the weight goal
        weightDiff= targetWeight-weight

        //points for weight goal
        if(weightDiff >0) {
            if(weightDiff>= 10)
                points += 4
            else
                points += 2
        } else if(weightDiff<0) {
            if(weightDiff <= -10)
                points +=0
            else
                points +=2
        } else {
            points += 2
        }

        //points for build goal
        if(user.buildGoal == "Strength")
        {
            points+=2
        }
        else if(user.buildGoal == "Hypertrophy")
        {
            points+=4
        }
        else if(user.buildGoal == "Endurance")
        {
            points+=6

        }

        /* // workout area
        if(user.workoutArea=="Core"){
            //recommend core workout
        } else if(user.workoutArea=="Cardio"){
            //recommend cardio workout
        }else if(user.workoutArea=="Flexibility"){
            //recommend flexibility workout
        }else if(user.workoutArea=="Lower Body"){
            //recommend lower body workout
        }else if(user.workoutArea=="Upper Body"){
            //recommend upper body workout
        }else if(user.workoutArea=="Full Body"){
            //recommend full body workout
        }*/

        //fit user into an personality profile based on the number of points
        if(points > 65) {
            recommendation.setText(R.string.six_day_profile)
            workout_plan.setText(R.string.six_day_plan)
        } else if(points >=30) {
            recommendation.setText(R.string.four_day_profile)
            workout_plan.setText(R.string.four_day_plan)
        } else {
            recommendation.setText(R.string.three_day_profile)
            workout_plan.setText(R.string.three_day_plan)
        }
    }
}