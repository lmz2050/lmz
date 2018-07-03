package cn.lmz.mike.data.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author  *****
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.TYPE })
@Documented
public @interface Ehcache {
	// 缓存名称,默认为空
	String cacheName() default "";
}
