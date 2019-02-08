package com.mhp.bootcamp.helloworld.aop;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PerformanceLogging {
}
