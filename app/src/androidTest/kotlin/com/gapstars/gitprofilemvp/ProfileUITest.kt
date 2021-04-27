package com.gapstars.gitprofilemvp

import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gapstars.gitprofilemvp.ui.profile.ProfileActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf

/**
 * Instrumented test, which will execute on an Android device.
 *
 */
@RunWith(AndroidJUnit4::class)
class ProfileUITest {

    @Rule
    var activityTestRule: ActivityScenarioRule<ProfileActivity> =
        ActivityScenarioRule(ProfileActivity::class.java)


    @Test
    fun checkViewVisibility() {

        val textView = onView(Matchers.allOf(withId(R.id.tv_profile_title), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        textView.check(matches(isDisplayed()))

        val textView2 = onView(Matchers.allOf(withId(R.id.tv_profile_title), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        textView2.check(matches(ViewMatchers.withText("Profile")))

        val imageView = onView(Matchers.allOf(withId(R.id.iv_profile), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        imageView.check(matches(isDisplayed()))

        val textView3 = onView(Matchers.allOf(withId(R.id.tv_owner_name), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        textView3.check(matches(isDisplayed()))

        val textView4 = onView(Matchers.allOf(withId(R.id.tv_git_name), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        textView4.check(matches(isDisplayed()))

        val textView5 = onView(Matchers.allOf(withId(R.id.tv_owner_email), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        textView5.check(matches(isDisplayed()))

        val textView6 = onView(Matchers.allOf(withId(R.id.tv_followers), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        textView6.check(matches(isDisplayed()))

        val textView7 = onView(Matchers.allOf(withId(R.id.tv_following), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        textView7.check(matches(isDisplayed()))

        val textView8 = onView(Matchers.allOf(withId(R.id.tv_Pinned), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        textView8.check(matches(isDisplayed()))

        val textView9 = onView(Matchers.allOf(withId(R.id.tv_Pinned), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        textView9.check(matches(ViewMatchers.withText("Pinned")))

        val textView10 = onView(Matchers.allOf(withId(R.id.tv_pinned_view_all), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        textView10.check(matches(isDisplayed()))

        val textView11 = onView(Matchers.allOf(withId(R.id.tv_pinned_view_all), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        textView11.check(matches(ViewMatchers.withText("View all")))

        val recyclerView = onView(Matchers.allOf(withId(R.id.rv_pinned_list), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        // verify recycler view is being displayed
        recyclerView.check(matches(isDisplayed()))

        val appCompatTextView = onView(Matchers.allOf(withId(R.id.tv_pinned_view_all), childAtPosition(childAtPosition(ViewMatchers.withClassName(Matchers.`is`("androidx.core.widget.NestedScrollView")), 0), 9), isDisplayed()))
        appCompatTextView.perform(click())

        val textView12 = onView(Matchers.allOf(withId(R.id.tv_top_repos), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        textView12.check(matches(isDisplayed()))

        val textView13 = onView(Matchers.allOf(withId(R.id.tv_top_repos), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        textView13.check(matches(ViewMatchers.withText("Top repositories")))

        val textView14 = onView(Matchers.allOf(withId(R.id.tv_top_repos_view_all), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        textView14.check(matches(isDisplayed()))

        val textView15 = onView(Matchers.allOf(withId(R.id.tv_top_repos_view_all), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        textView15.check(matches(ViewMatchers.withText("View all")))

        val recyclerView2 = onView(Matchers.allOf(withId(R.id.rv_top_repos_list), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        recyclerView2.check(matches(isDisplayed()))

        val textView16 = onView(Matchers.allOf(withId(R.id.tv_starred_repos), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        textView16.check(matches(isDisplayed()))

        val textView17 = onView(Matchers.allOf(withId(R.id.tv_starred_repos), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        textView17.check(matches(ViewMatchers.withText("Starred repositories")))

        val textView18 = onView(Matchers.allOf(withId(R.id.tv_starred_repos_view_all), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        textView18.check(matches(isDisplayed()))

        val textView19 = onView(Matchers.allOf(withId(R.id.tv_starred_repos_view_all), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        textView19.check(matches(ViewMatchers.withText("View all")))

        val recyclerView3 = onView(Matchers.allOf(withId(R.id.rv_starred_repos_list), ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(ScrollView::class.java))), isDisplayed()))
        recyclerView3.check(matches(isDisplayed()))

        val appCompatTextView2 = onView(Matchers.allOf(withId(R.id.tv_top_repos_view_all), childAtPosition(childAtPosition(ViewMatchers.withClassName(Matchers.`is`("androidx.core.widget.NestedScrollView")), 0), 12), isDisplayed()))
        appCompatTextView2.perform(click())

        val appCompatTextView3 = onView(Matchers.allOf(withId(R.id.tv_starred_repos_view_all), childAtPosition(childAtPosition(ViewMatchers.withClassName(Matchers.`is`("androidx.core.widget.NestedScrollView")), 0), 15), isDisplayed()))
        appCompatTextView3.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}