package com.aoe.astalift.web.annotation;

import java.lang.annotation.*;

/**
 * Created by joey on 16-3-21.
 * indicate  that a controller does not need check session
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SessionValidIgnore {
    boolean value() default true;
}
