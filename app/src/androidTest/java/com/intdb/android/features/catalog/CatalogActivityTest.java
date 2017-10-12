package com.intdb.android.features.catalog;


import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.intdb.android.R;
import com.intdb.android.features.catalog.view.impl.CatalogActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)

public class CatalogActivityTest {

    @Rule
    public ActivityTestRule<CatalogActivity> mActivityTestRule = new ActivityTestRule<>(CatalogActivity.class);


    @Before
    public void setUp() throws Exception {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkActionbarItemsVisiblility(){
        ViewInteraction about = onView(withId(R.id.action_reload));
        ViewInteraction logout = onView(withId(R.id.action_about)).perform();

        about.check(matches(isDisplayed()));
        logout.check(matches(isDisplayed()));
    }

    @Test
    public void checkScrollViewVisibility(){

        ViewInteraction scroll = onView(withId(R.id.root_scroll));

        scroll.check(matches(isDisplayed()));
    }

    @Test
    public void checkScrollViewHasCarousels(){

        ViewInteraction container = onView(withId(R.id.container_carousel));

        container.check(matches(hasDescendant(withId(R.id.popular_include_layout_carousel))));
    }

    @Test
    public void checkScrollViewHasPopularCarousel(){

        ViewInteraction textView = onView(withText(
                InstrumentationRegistry.getTargetContext().getString(R.string.carousel_title_popular)
        ));

        textView.check(matches(isDisplayed()));
    }
    @Test
    public void checkScrollViewHasPopularTopRated(){

        ViewInteraction textView = onView(withText(
                InstrumentationRegistry.getTargetContext().getString(R.string.carousel_title_top_rated)
        ));

        textView.check(matches(isDisplayed()));
    }
    @Test
    public void checkScrollViewHasPopularRevenue(){

        ViewInteraction textView = onView(withText(
                InstrumentationRegistry.getTargetContext().getString(R.string.carousel_title_top_revenue)
        ));

        textView.check(matches(isDisplayed()));
    }

}
