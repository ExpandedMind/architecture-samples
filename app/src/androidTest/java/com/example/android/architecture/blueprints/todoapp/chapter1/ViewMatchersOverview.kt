package com.example.android.architecture.blueprints.todoapp.chapter1

import android.view.inputmethod.EditorInfo
import android.widget.CheckBox
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.LayoutMatchers.hasEllipsizedText
import androidx.test.espresso.matcher.LayoutMatchers.hasMultilineText
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasImeAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods
import androidx.test.espresso.matcher.ViewMatchers.withChild
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.hamcrest.CoreMatchers
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ViewMatchersOverview {

    @Test
    fun userProperties() {
        Espresso.onView(ViewMatchers.withId(R.id.add_task_fab))
        Espresso.onView(ViewMatchers.withText("You have no tasks!"))
    }

    @Test
    fun uiProperties() {
        onView(isDisplayed())
        onView(isEnabled())
    }

    @Test
    fun hierarchy() {
        onView(withParent(withId(R.id.tasks_list)))
        onView(withChild(withId(R.id.complete_checkbox)))
    }

    @Test
    fun input() {
        onView(supportsInputMethods()) // https://github.com/android/testing-samples.git
        onView(hasImeAction(EditorInfo.IME_ACTION_SEND))
    }

    @Test
    fun classMatchers() {
        onView(isAssignableFrom(CheckBox::class.java))
        onView(withClassName(CoreMatchers.`is`(FloatingActionButton::class.java.getCanonicalName())))
    }


    @Test
    fun layoutMatchers() {
        onView(hasEllipsizedText())
        onView(hasMultilineText())
    }

}