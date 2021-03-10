package com.example.android.architecture.blueprints.todoapp

import androidx.test.rule.ActivityTestRule
import com.example.android.architecture.blueprints.todoapp.tasks.TasksActivity
import org.junit.Rule
import org.junit.rules.TestRule


/**
 * Represents base utilities for all UI tests
 */
open class BaseTest {

    /**
     * This is the activity from where all Base tests will run as starting point
     */

    @get:Rule
    val activityTestRule: TestRule = ActivityTestRule(TasksActivity::class.java)

}
