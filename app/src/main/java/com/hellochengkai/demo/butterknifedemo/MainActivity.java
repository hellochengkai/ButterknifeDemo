package com.hellochengkai.demo.butterknifedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hellochengkai.demo.butterknifedemo.myhBind.BindView;
import com.hellochengkai.demo.butterknifedemo.myhBind.MyBindClick;
import com.hellochengkai.demo.butterknifedemo.myhBind.MyBindView;
import com.hellochengkai.demo.butterknifedemo.myhBind.MyOnClickListener;
import com.hellochengkai.demo.butterknifedemo.myhBind.MyOnClickListenerFor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @MyOnClickListenerFor(listener = MyOnClickListener.class)
    @MyBindView(id = R.id.button1)
    private Button button1;

    @MyBindView(id = R.id.button)
    private Button button;

    @MyBindClick(id = {R.id.button})
    public void onClick(View v) {
        String msg = "is a click ";
        switch (v.getId()) {
            case R.id.button: {
                msg += "button";
                break;
            }
            case R.id.button1: {
                msg += "button1";
                break;
            }
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test(Test.class);
        BindView.bind(this);
//        ButterKnife.bind(this);
    }

    void test(Class<?> cls) {
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            Log.d(TAG, "test: method " + method);
            Annotation[] annotations = null;
            annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                Log.d(TAG, "test: annotation " + annotation);
            }
        }
    }
}
