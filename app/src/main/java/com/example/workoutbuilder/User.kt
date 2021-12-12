package com.example.workoutbuilder

// For setting up the user information -> values are passed through this class from
// the Main Activity after getting all of the answers from the user

data class User(var age: Int, var height: Int, var weight: Int, var targetWeight: Int, var length: String, var frequency: String, var fitnessLevelNow: String, var buildGoal: String /*var workoutArea: String, var weights: String, var gym: String*/)