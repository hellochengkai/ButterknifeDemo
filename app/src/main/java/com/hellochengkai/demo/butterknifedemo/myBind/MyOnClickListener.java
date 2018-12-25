package com.hellochengkai.demo.butterknifedemo.myBind;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.hellochengkai.demo.butterknifedemo.R;

/**
 * Created by chengkai on 18-12-24.
 */

public class MyOnClickListener{
    private static final String TAG = "MyOnClickListener";

    public void onClick(Context context,View v) {
        String msg = "is a click ";
        switch (v.getId()){
            case R.id.button:{
                msg += "button";
                break;
            }
            case R.id.button1:{
                msg += "button1";
                break;
            }
        }
        Log.d(TAG, "onClick: " + msg);
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
