package com.hellochengkai.demo.butterknifedemo.myBind;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by chengkai on 18-12-24.
 */


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyOnClickListenerFor {
    Class<? extends MyOnClickListener> listener();
}
