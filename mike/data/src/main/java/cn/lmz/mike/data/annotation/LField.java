package cn.lmz.mike.data.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface LField {

	public String name() default "";
	public boolean useForDb() default true;
	public String sql() default "";
}
