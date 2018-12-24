package com.hellochengkai.demo.butterknifedemo;

import android.util.Log;

/**
 * Created by chengkai on 18-12-24.
 */

public class Test {

    private static final String TAG = "Test";
    @MyTag(name = "haha")
    private void info()
    {
        Log.d(TAG, "info: ");
    }

    public void aaa()
    {
        info();
    }
}
