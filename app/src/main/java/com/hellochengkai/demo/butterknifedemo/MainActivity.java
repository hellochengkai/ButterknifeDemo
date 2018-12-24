package com.hellochengkai.demo.butterknifedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    int a = 0;

    private static final String TAG = "MainActivity";
    @BindViewAble(id = R.id.button)
    Button button;

    @OnClick({R.id.button})
    public void onClick()
    {
        Toast.makeText(this, "is a click", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test(Test.class);
        com.hellochengkai.demo.butterknifedemo.BindView.bind(this);
        button.setText("aaaa");
        ButterKnife.bind(this);
    }

    void test(Class<?> cls)
    {
        Method[] methods = cls.getDeclaredMethods();
        for (Method method:methods){
            Log.d(TAG, "test: method " + method);
            Annotation[] annotations = null;
            annotations = method.getDeclaredAnnotations();
            for (Annotation annotation:annotations){
                Log.d(TAG, "test: annotation " + annotation);
            }
        }
    }
}
