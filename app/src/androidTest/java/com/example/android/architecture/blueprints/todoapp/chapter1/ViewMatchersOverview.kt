package com.example.android.architecture.blueprints.todoapp.chapter1

import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.R
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ViewMatchersOverview {

    @Test
    fun userProperties() {
        Espresso.onView(ViewMatchers.withId(R.id.add_task_fab))
        Espresso.onView(ViewMatchers.withText("You have no tasks!"))
    }
}