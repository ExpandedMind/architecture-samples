package com.example.android.architecture.blueprints.todoapp.chapter1

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import com.example.android.architecture.blueprints.todoapp.BaseTest
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.chapter2.CompleteToDoViewAction
import org.hamcrest.CoreMatchers
import org.junit.Test

@LargeTest
class RecyclerViewActionsTest : BaseTest() {

    @Test
    fun addToDos_scrollOverItems() {
        Utils.generateToDos(12)
        onView(withId(R.id.tasks_list))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(10, scrollTo()))
                .perform(scrollToPosition<RecyclerView.ViewHolder>(1))
                .perform(scrollToPosition<RecyclerView.ViewHolder>(11))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(11, click()))
        Espresso.pressBack()
        onView(withId(R.id.tasks_list)).perform(scrollToPosition<RecyclerView.ViewHolder>(2))
    }

    /**
     * It did not crash. Actually last task is not visible, however Espresso can still interact with it
     * which is awesome. In the past, we could not interact with a view if it was not visible, Espresso used
     * to throw exception. As our recycler view is aware of how many items it has, it can still interact
     * with them even if those are "hidden" or not visible in the screen.
     */
    @Test
    fun addToDos_tryToPerformActionsOnInvisibleItems() {
        Utils.generateToDos(12)
        onView(withId(R.id.tasks_list))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(11, click()))
        Thread.sleep(2000)
        Espresso.pressBack()
    }

    @Test
    fun completeTodoTask_byUsingCustomRecyclerViewAction() {
        Utils.generateToDos(3)

        onView(withId(R.id.tasks_list)).perform(CompleteToDoViewAction("Task # 2"))

        onView(CoreMatchers.allOf(withId(R.id.complete_checkbox), hasSibling(withText("Task # 2"))))
                .check(matches(isChecked()))
    }


    @Test(expected = PerformException::class)
    fun completeTodoTask_givenErroneousTitle_throwsException() {
        Utils.generateToDos(3)

        onView(withId(R.id.tasks_list)).perform(CompleteToDoViewAction("Task # 2 sfdf"))
    }
}