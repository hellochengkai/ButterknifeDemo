package com.hellochengkai.demo.butterknifedemo;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by chengkai on 18-12-24.
 */

public class BindView {

    private static final String TAG = "BindView";
    public static boolean bind(Activity activity)
    {
        Field [] fields = activity.getClass().getDeclaredFields();

        for (Field field : fields){
            if(View.class.isAssignableFrom(field.getType())){
                Log.d(TAG, "bind:field " + field);
                Annotation[] annotations = field.getDeclaredAnnotations();
                for (Annotation annotation : annotations){
                    Log.d(TAG, "bind: annotations = " + annotation);

                    BindViewAble bindViewAble = field.getAnnotation(BindViewAble.class);
                    if(bindViewAble != null){
                       int id = bindViewAble.id();
                       field.setAccessible(true);
                        try {
                            field.set(activity,activity.findViewById(id));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
//                    if(annotation.annotationType().equals(BindViewAble.class))
//                    {
//                        Class<? extends BindViewAble> bind = field.getAnnotation(BindViewAble.class);
//                        Log.d(TAG, "bind:annotation is BindViewAble");
//                    }
                }
            }else {
                Log.d(TAG, "bind: " + field + " is not view");
            }

        }

        return true;
    }

}
