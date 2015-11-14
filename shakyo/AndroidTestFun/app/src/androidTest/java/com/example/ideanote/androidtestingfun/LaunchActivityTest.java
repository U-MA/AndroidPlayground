package com.example.ideanote.androidtestingfun;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;

public class LaunchActivityTest extends ActivityUnitTestCase<LaunchActivity> {

    private Intent launchIntent;

    public LaunchActivityTest() {
        super(LaunchActivity.class);
    }

    @Override
    protected void setActivity(Activity testActivity) {
        if (testActivity != null)
            testActivity.setTheme(R.style.AppTheme);
        super.setActivity(testActivity);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        launchIntent = new Intent(getInstrumentation().getTargetContext(),
                LaunchActivity.class);
    }

    @MediumTest
    public void testPrecondition() {
        startActivity(launchIntent, null, null);
        final Button launchNextButton = (Button) getActivity().findViewById(R.id.button);

        assertNotNull("launchActivity is null", getActivity());
        assertNotNull("launchNextButton is null", launchNextButton);
    }

    @MediumTest
    public void testLaunchNextActivityButton_labelText() {
        startActivity(launchIntent, null, null);
        final Button launchNextButton = (Button) getActivity().findViewById(R.id.button);

        final String expectedButtonText = getActivity().getString(R.string.launch_next);
        assertEquals("Unexpected button label text", expectedButtonText, launchNextButton.getText());
    }

    @MediumTest
    public void testNextActivityWasLaunchedWithIntent() {
        startActivity(launchIntent, null, null);
        final Button launchNextButton = (Button) getActivity().findViewById(R.id.button);
        launchNextButton.performClick();

        final Intent launchIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", launchIntent);
        assertTrue(isFinishCalled());

        final String payload = launchIntent.getStringExtra(NextActivity.EXTRAS_PAYLOAD_KEY);
        assertEquals("Payload is empty", LaunchActivity.STRING_PAYLOAD, payload);
    }
}
