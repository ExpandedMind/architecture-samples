package com.example.android.architecture.blueprints.todoapp.chapter4

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.azimolabs.conditionwatcher.ConditionWatcher
import com.azimolabs.conditionwatcher.Instruction
import org.hamcrest.Matcher
import java.lang.Exception

object ConditionWatchers {

    @Throws(Exception::class)
    @JvmStatic
    fun waitForElement(interaction: ViewInteraction, timeout: Int = 5000): ViewInteraction {
        ConditionWatcher.setTimeoutLimit(timeout)
        ConditionWatcher.waitForCondition(object : Instruction() {

            override fun getDescription(): String = "waitForElement"

            override fun checkCondition(): Boolean {
                try {
                    interaction.check(matches(isDisplayed()))
                    return true
                } catch (nmve: NoMatchingViewException) {
                    return false
                }
            }
        })
        return interaction
    }

    @Throws(Exception::class)
    @JvmStatic
    fun waitForElementIsGone(viewMatcher: Matcher<View>, timeout: Int = 5000){
        ConditionWatcher.setTimeoutLimit(timeout)
        ConditionWatcher.waitForCondition(object : Instruction() {

            override fun getDescription(): String = "waitForElementIsGone"

            override fun checkCondition(): Boolean {
                try {
                    onView(viewMatcher).check(matches(isDisplayed()))
                    return false
                } catch (nmve: NoMatchingViewException) {
                    // Still wait for the element, it does not match condition yet.
                    return true
                }
            }
        })
    }

    @Throws(Exception::class)
    @JvmStatic
    fun waitForElementIsGone(
            interaction: ViewInteraction,
            timeout: Int = 5000): ViewInteraction {
        ConditionWatcher.setTimeoutLimit(timeout)
        ConditionWatcher.waitForCondition(object : Instruction() {

            override fun getDescription(): String {
                return "waitForElementIsGone"
            }

            override fun checkCondition(): Boolean {
                try {
                    interaction.check(matches(isDisplayed()))
                    return false
                } catch (ex: NoMatchingViewException) {
                    return true
                }

            }
        })
        return interaction
    }


}