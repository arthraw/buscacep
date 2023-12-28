package com.project.buscacep

import org.junit.Assert.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import com.project.buscacep.presentation.MainActivity
import org.junit.Test
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class UiTests {

    @get: Rule
    var intentRule = IntentsTestRule(MainActivity::class.java)


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.project.buscacep", appContext.packageName)
    }

    @Test
    fun testScreenUp() {
        onView(withId(R.id.textView)).check(matches(isDisplayed()))
        onView(withId(R.id.cep)).check(matches(isDisplayed()))
        onView(withId(R.id.fullAddress)).check(matches(isDisplayed()))
        onView(withId(R.id.searchBtn)).check(matches(isDisplayed()))
    }

    @Test
    fun testCepInvalid() {
        onView(withId(R.id.cep)).perform(replaceText("99999999"))
        onView(withId(R.id.searchBtn)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.fullAddress)).check(matches(withText("Erro: Cep invalido.")))
    }
}