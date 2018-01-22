package com.zjcds.common.jpa.annotation;

import java.lang.annotation.*;

/**
 * 标记注解,标记使用最近方法调用的注解EntityGraph
 * created date：2017-09-07
 * @author niezhegang
 */
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface NearestEntityGraph {

}
