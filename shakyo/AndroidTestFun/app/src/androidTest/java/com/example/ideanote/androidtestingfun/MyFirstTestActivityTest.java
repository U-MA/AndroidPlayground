package com.example.ideanote.androidtestingfun;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class MyFirstTestActivityTest extends ActivityInstrumentationTestCase2<MyFirstActivity> {

    private MyFirstActivity myFirstActivity;
    private TextView textView;

    public MyFirstTestActivityTest() {
        super(MyFirstActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        myFirstActivity = getActivity();
        textView = (TextView) myFirstActivity.findViewById(R.id.text_view);
    }

    public void testPreconditions() {
        assertNotNull("myFirstActivity is null", myFirstActivity);
        assertNotNull("textView is null", textView);
    }

    public void testMyFirstTestTextView_labelText() {
        final String expected = myFirstActivity.getString(R.string.my_first_test);
        final String actual = textView.getText().toString();
        assertEquals("textView contains wrong text", expected, actual);
    }
}
