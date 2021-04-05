package com.example.android.architecture.blueprints.todoapp.chapter1

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.android.architecture.blueprints.todoapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * Utility object for common interactions/flows/matchers used throughout the Test suites in this project.
 */
object Utils {

    fun generateToDos(howMany: Int): Unit {
        val taskAddedSnackbarInteraction = Espresso.onView(ViewMatchers.withText(R.string.successfully_added_task_message))
        for (i in 1..howMany) {
            // Adding a new Task
            val taskTitle = "Task # $i"
            val taskDescription = "Doing thing number $i"
            Espresso.onView(ViewMatchers.isAssignableFrom(FloatingActionButton::class.java)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.add_task_title_edit_text))
                    .perform(ViewActions.typeText(taskTitle))
            Espresso.onView(ViewMatchers.withId(R.id.add_task_description_edit_text))
                    .perform(ViewActions.typeText(taskDescription), ViewActions.closeSoftKeyboard())
            Espresso.onView(ViewMatchers.withId(R.id.save_task_fab)).perform(ViewActions.click())
            //Apparently we don't require this extra waiting. Espresso 3.0 already syncs snackbars and interactions
//            ConditionWatchers.waitForElement(taskAddedSnackbarInteraction, 3000)
        }
    }
}