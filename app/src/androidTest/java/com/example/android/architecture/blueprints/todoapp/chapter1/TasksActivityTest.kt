package com.example.android.architecture.blueprints.todoapp.chapter1

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasChildCount
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.BaseTest
import com.example.android.architecture.blueprints.todoapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TasksActivityTest : BaseTest() {

    /**
     * Add a new TO-DO that provides the title and description. Verify it is shown in the TO-DO list.
     */
    @Test
    fun addNewTODO_verifyItIsShown() {
        val taskTitle = "Rudiments"
        val taskDescription = " Practice rotated paradidles"

        onView(isAssignableFrom(FloatingActionButton::class.java)).perform(ViewActions.click())
        onView(withId(R.id.add_task_title_edit_text)).perform(ViewActions.typeText(taskTitle))
        onView(withId(R.id.add_task_description_edit_text))
                .perform(ViewActions.typeText(taskDescription))

        onView(withId(R.id.save_task_fab)).perform(ViewActions.click())

        onView(withId(R.id.title_text)).check(ViewAssertions.matches(allOf(
                ViewMatchers.withText(taskTitle),ViewMatchers.isDisplayed())))
    }

    /**
     * Add a new TO-DO, mark it completed, and verify it is in the list of completed TO-DOs.
     */
    @Test
    fun addNewTODO_verifyItIsOnCompletedList() {

    }

    /**
     * Add a new TO-DO, edit it, and verify the changes.
     */
    @Test
    fun addNewTODO_editItAndSeeChanges() {

    }

}