package com.hellochengkai.demo.butterknifedemo.myBind;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by chengkai on 18-12-24.
 */

public class BindView {

    private static final String TAG = "BindView";

    public static void bind(Activity activity) {
        findView(activity);
        setOnClick(activity);
        setOnClickListener(activity);
    }


    private static void findView(Activity activity) {
        Field[] fields = activity.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (View.class.isAssignableFrom(field.getType())) {
                Log.d(TAG, "bind:field " + field);
                Annotation[] annotations = field.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    Log.d(TAG, "bind: annotations = " + annotation);
                    if (annotation.annotationType().equals(MyBindView.class)) {
                        MyBindView bindViewAble = field.getAnnotation(MyBindView.class);
                        if (bindViewAble != null) {
                            int id = bindViewAble.id();
                            field.setAccessible(true);
                            try {
                                field.set(activity, activity.findViewById(id));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            } else {
                Log.d(TAG, "bind: " + field + " is not view");
            }
        }
    }
    private static void setOnClick(final Activity activity) {
        Method[] methods = activity.getClass().getDeclaredMethods();
        for (final Method method : methods) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(MyBindClick.class)) {
                    MyBindClick myOnClick = method.getAnnotation(MyBindClick.class);
                    int[] ids = myOnClick.id();
                    for (int id : ids) {
                        if(activity.findViewById(id) instanceof Button){
                            Button button = activity.findViewById(id);
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Log.d(TAG, "setOnClick: ");
                                    try {
                                        method.invoke(activity,v);
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    } catch (InvocationTargetException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    private static void setOnClickListener(final Activity activity)
    {
        Field [] fields = activity.getClass().getDeclaredFields();
        for (Field field: fields){
            MyOnClickListenerFor myOnClickListenerFor = field.getAnnotation(MyOnClickListenerFor.class);
            if(myOnClickListenerFor != null){
                Class<? extends MyOnClickListener> myOnClickListener = myOnClickListenerFor.listener();
                try {
                    final MyOnClickListener listener = myOnClickListener.newInstance();
                    field.setAccessible(true);
                    Button button = (Button) field.get(activity);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(final View v) {
                            Log.d(TAG, "onClick: setOnClickListener ");
                            listener.onClick(activity,v);
                        }
                    });
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
