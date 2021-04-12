package com.example.android.architecture.blueprints.todoapp.chapter2

import android.view.View
import android.view.ViewConfiguration
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.*
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.util.HumanReadables
import com.example.android.architecture.blueprints.todoapp.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

/**
 * RecyclerView action that performs click on TO-DO checkbox based on its title.
 */
class CompleteToDoViewAction(val withTitle: String) : ViewAction {

    override fun getDescription(): String = "Complete ToDo with title: $withTitle by clicking its checkbox."

    override fun getConstraints(): Matcher<View> = allOf(isAssignableFrom(RecyclerView::class.java), isDisplayed())

    override fun perform(uiController: UiController, view: View?) {
        var foundTitleView: Boolean = false
        try {
            val recyclerView = view as RecyclerView
            val recyclerAdapter = recyclerView.adapter
            if (recyclerAdapter != null) {
                for (index in 0..recyclerAdapter.itemCount) {
                    val viewHolder = recyclerView.findViewHolderForAdapterPosition(index)
                    val titleTextView = viewHolder?.itemView?.findViewById<TextView>(R.id.title_text)
                    if (titleTextView?.text == withTitle) {
                        val todoCheckBox: CheckBox = viewHolder.itemView.findViewById(R.id.complete_checkbox)
                        todoCheckBox.performClick()
                        foundTitleView = true
                        return
                    }
                }
            }
            uiController.loopMainThreadForAtLeast(ViewConfiguration.getTapTimeout().toLong())
        } catch (re: RuntimeException) {

            throw PerformException.Builder().withActionDescription(this.getDescription())
                    .withViewDescription(HumanReadables.describe(view)).withCause(re).build()
        } finally {
            if (!foundTitleView) {
                throw PerformException.Builder().withActionDescription(this.description)
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(java.lang.RuntimeException("Could not find any toDo with title $withTitle"))
                        .build()
            }
        }
    }

}