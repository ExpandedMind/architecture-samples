package com.example.android.architecture.blueprints.todoapp.chapter2

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.android.architecture.blueprints.todoapp.BaseTest
import com.example.android.architecture.blueprints.todoapp.R
import com.example.android.architecture.blueprints.todoapp.chapter1.Utils
import org.junit.Test

class CustomActionsTest : BaseTest() {

    val customSwipeManager: CustomSwipeActions = CustomSwipeActions()

    @Test
    fun useStandardSwipeDownToPullRefresh() {
        Utils.generateToDos(12)
        onView(withId(R.id.tasks_list)).perform(swipeDown())
        Thread.sleep(25000)
    }


    @Test
    fun useCustomSwipeDuration() {
        Utils.generateToDos(3)
        onView(withId(R.id.tasks_list))
                .perform(customSwipeManager.swipeCustom(2000, GeneralLocation.TOP_CENTER, GeneralLocation.BOTTOM_CENTER))
        Thread.sleep(2500)
    }
}