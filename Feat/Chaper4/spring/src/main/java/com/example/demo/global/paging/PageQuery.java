package com.example.demo.global.paging;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PageQuery {
	String value() default "page";
}
