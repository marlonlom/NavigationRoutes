package com.github.marlonlom.demos.navigationroutes;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.Root;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ExampleInstrumentedTest extends InstrumentationTestCase {
  /**
   * The rule.
   */
  @Rule
  public final ActivityTestRule<MainActivity> mRule = new ActivityTestRule<MainActivity>(
      MainActivity.class);
  private Instrumentation.ActivityMonitor mNextActivityViewMonitor;
  private Instrumentation.ActivityMonitor mHelloActivityViewMonitor;

  /**
   * Sets .
   */
  @Before
  public void setup() {
    injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    mNextActivityViewMonitor = new Instrumentation.ActivityMonitor(NextActivity.class.getName(),
        null, false);
    mHelloActivityViewMonitor = new Instrumentation.ActivityMonitor(HelloActivity.class.getName(),
        null, false);
    getInstrumentation().addMonitor(mNextActivityViewMonitor);
    getInstrumentation().addMonitor(mHelloActivityViewMonitor);
  }

  /**
   * Should navigate to next screen success.
   */
  @Test
  public void shouldNavigateToNextScreenSuccess() {
    Espresso.onView(ViewMatchers.withId(R.id.button_navigate_next_window))
        .perform(ViewActions.click());
    Activity activity = mNextActivityViewMonitor.waitForActivityWithTimeout(5 * 1000);
    assertNotNull("Activity was not started", activity);
  }

  /**
   * Should not navigate to next screen empty text.
   */
  @Test
  public void shouldNotNavigateToNextScreenEmptyText() {
    Espresso.onView(ViewMatchers.withId(R.id.button_navigate_with_text))
        .perform(ViewActions.click());

    Espresso.onView(ViewMatchers.withText(R.string.text_toast_hello_empty))
        .inRoot(RootMatchers.withDecorView(
            Matchers.not(Matchers.is(
                mRule.getActivity().getWindow().getDecorView()))))
        .check(matches(isDisplayed()));

  }

  /**
   * Should navigate to next screen with text.
   */
  @Test
  public void shouldNavigateToNextScreenWithText() {
    Espresso.onView(withId(R.id.editText_hello)).perform(
        ViewActions.typeText(mRule.getActivity().getString(R.string.app_name)));
    Espresso.onView(ViewMatchers.withId(R.id.button_navigate_with_text))
        .perform(ViewActions.click());
    Activity activity = mHelloActivityViewMonitor.waitForActivityWithTimeout(5 * 1000);
    assertNotNull("Activity was not started", activity);
  }

  /**
   * Should not navigate to next screen empty text.
   */
  @Test
  public void shouldNotNavigateToNextScreenEmptyParcelableWithText() {
    Espresso.onView(ViewMatchers.withId(R.id.button_navigate_with_parcel))
        .perform(ViewActions.click());

    Espresso.onView(ViewMatchers.withText(R.string.text_toast_hello_empty))
        .inRoot(RootMatchers.withDecorView(
            Matchers.not(Matchers.is(
                mRule.getActivity().getWindow().getDecorView()))))
        .check(matches(isDisplayed()));

  }

  /**
   * Should navigate to next screen with text.
   */
  @Test
  public void shouldNavigateToNextScreenParcelableWithText() {
    Espresso.onView(withId(R.id.editText_hello)).perform(
        ViewActions.typeText(mRule.getActivity().getString(R.string.app_name)));
    Espresso.onView(ViewMatchers.withId(R.id.button_navigate_with_parcel))
        .perform(ViewActions.click());

    Activity activity = mHelloActivityViewMonitor.waitForActivityWithTimeout(5 * 1000);
    assertNotNull("Activity was not started", activity);
  }
}
