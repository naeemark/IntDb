package com.intdb.android.functional.features.details;


import android.content.Intent;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.intdb.android.R;
import com.intdb.android.features.details.ItemDetailsActivity;
import com.intdb.android.model.Movie;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ItemDetailsActivityTest {

    // third parameter is set to false which means the activity is not started automatically
    @Rule
    public ActivityTestRule<ItemDetailsActivity> mActivityTestRule = new ActivityTestRule<>(ItemDetailsActivity.class, false, false);

    @Before
    public void setUp() throws Exception {

        Intent intent = new Intent();
        intent.putExtra(ItemDetailsActivity.EXTRA_MOVIE, getMovieStub());
        mActivityTestRule.launchActivity(intent);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Movie getMovieStub() {

            return new Movie(
                    1,
                    "Test",
                    "Test",
                    "Test",
                    "Test",
                    "Test",
                    "Test",
                    2.2,
                    2.1,
                    0
            );
        }

        @Test
        public void isBackVisible () {
            ViewInteraction imageButton = onView(
                    allOf(withContentDescription("Navigate up"),
                            isDisplayed()));
            imageButton.check(matches(isDisplayed()));
        }

        @Test
        public void isScreenTitleVisible () {
            ViewInteraction textView = onView(
                    allOf(withText(R.string.lbl_activity_details),
                            isDisplayed()));
            textView.check(matches(isDisplayed()));
        }

        @Test
        public void isImageViewVisible () {
            ViewInteraction dp = onView(withId(R.id.imageView_banner));

            dp.check(matches(isDisplayed()));
        }

        @Test
        public void isTitleVisible() {
            ViewInteraction textView = onView(
                    allOf(withId(R.id.title),
                            isDisplayed()));
            textView.check(matches(isDisplayed()));
        }

        @Test
        public void isReleasedVisible() {
            ViewInteraction textView = onView(
                    allOf(withId(R.id.released),
                            isDisplayed()));
            textView.check(matches(isDisplayed()));
        }

        @Test
        public void isRatingVisible() {
            ViewInteraction textView = onView(
                    allOf(withId(R.id.ratingBar),
                            isDisplayed()));
            textView.check(matches(isDisplayed()));
        }

        @Test
        public void isOverviewVisible() {
            ViewInteraction textView = onView(
                    allOf(withId(R.id.description),
                            isDisplayed()));
            textView.check(matches(isDisplayed()));
        }
    }
