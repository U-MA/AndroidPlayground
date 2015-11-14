package com.example.ideanote.androidtestingfun;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class ClickFunActivityTest extends ActivityInstrumentationTestCase2<ClickFunActivity> {

    private ClickFunActivity clickFunActivity;
    private Button clickButton;
    private TextView textView;

    public ClickFunActivityTest() {
        super(ClickFunActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(true);

        clickFunActivity = getActivity();

        clickButton = (Button) clickFunActivity.findViewById(R.id.launch_next_activity_button);
        textView = (TextView) clickFunActivity.findViewById(R.id.info_text_view);
    }

    @MediumTest
    public void testPreconditions() {
        assertNotNull("clickFunActivity is null", clickFunActivity);
        assertNotNull("clickMeButton is null", clickButton);
        assertNotNull("textView is null", textView);
    }

    @MediumTest
    public void testClickMeButton_layout() {
        final View decorView = clickFunActivity.getWindow().getDecorView();

        ViewAsserts.assertOnScreen(decorView, clickButton);

        final ViewGroup.LayoutParams layoutParams = clickButton.getLayoutParams();
        assertNotNull(layoutParams);
        assertEquals(layoutParams.width, WindowManager.LayoutParams.MATCH_PARENT);
        assertEquals(layoutParams.height, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    @MediumTest
    public void testClickMeButton_labelText() {
        final String expectedNextButtonText = clickFunActivity.getString(R.string.click_me);
        final String actualNextButtonText = clickButton.getText().toString();
        assertEquals(expectedNextButtonText, actualNextButtonText);
    }

    @MediumTest
    public void testInfoTextView_layout() {
        final View decorView = clickFunActivity.getWindow().getDecorView();

        ViewAsserts.assertOnScreen(decorView, textView);
        assertTrue(View.GONE == textView.getVisibility());
    }

    @MediumTest
    public void testInfoTextView_isEmpty() {
        assertEquals("", textView.getText());
    }

    @MediumTest
    public void testInfoTextView_clickButtonAndExpectInfoText() {
        String expectedInfoText = clickFunActivity.getString(R.string.button_clicked);
        TouchUtils.clickView(this, clickButton);
        assertTrue(View.VISIBLE == textView.getVisibility());
        assertEquals(expectedInfoText, textView.getText());
    }
}
