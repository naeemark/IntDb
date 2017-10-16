package com.intdb.android.functional;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.intdb.android.R;
import com.intdb.android.features.catalog.view.impl.CatalogActivity;
import com.intdb.android.utils.TestAssets;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class CatalogActivityTest {

    private static final String TAG = CatalogActivityTest.class.getSimpleName();

    private MockWebServer mockWebServer;

    @Rule
    public ActivityTestRule<CatalogActivity> activityRule = new ActivityTestRule<>(CatalogActivity.class, true, false);

    @Before
    public void setUp() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
//        ApiClient.setBaseUrl(mockWebServer.url("/").toString());
    }

    @After
    public void shutDownServer() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    public void testMoviesShown() {
        initWithExpectedResponse();

//        onView(withId(R.id.textView_carousel_loading)).check(matches(not(isDisplayed())));
        onView(withId(R.id.root_scroll)).check(matches(hasDescendant(withId(R.id.textView_carousel_title))));
    }

    private void initWithExpectedResponse() {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(TestAssets.getPlacesListResponse(getInstrumentation().getContext())));

        //second page or update content
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(TestAssets.getPlacesListResponse2(getInstrumentation().getContext())));

        activityRule.launchActivity(new Intent());
//       Spoon.screenshot(activityRule.getActivity(), TAG);

    }

}
