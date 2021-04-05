package com.example.android.architecture.blueprints.todoapp.chapter2

import android.view.View
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.CoordinatesProvider
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.GeneralSwipeAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.ViewActions.actionWithAssertions

class CustomSwipeActions {

    /**
     * Fully customizable Swipe action for any need
     * @param duration length of time a custom swipe should last for, in milliseconds.
     * @param from for example [GeneralLocation.CENTER]
     * @param to for example [GeneralLocation.BOTTOM_CENTER]
     */
    fun swipeCustom(duration: Int, from: GeneralLocation, to: GeneralLocation): ViewAction {
        CustomSwipe.CUSTOM_DURATION.setSwipeDuration(duration)
        return actionWithAssertions(GeneralSwipeAction(
                CustomSwipe.CUSTOM_DURATION,
                translate(from, 0f, 0f),
                to,
        Press.FINGER))
    }

    /**
     * Translates the given coordinates by the given distances. The distances are given in term
     * of the view's size -- 1.0 means to translate by an amount equivalent to the view's length.
     */
    private fun translate(coords: CoordinatesProvider,
                          dx: Float, dy: Float): CoordinatesProvider? {
        return object : CoordinatesProvider {
            override fun calculateCoordinates(view: View): FloatArray? {
                val xy: FloatArray = coords.calculateCoordinates(view)
                xy[0] += dx * view.width
                xy[1] += dy * view.height
                return xy
            }
        }
    }
}