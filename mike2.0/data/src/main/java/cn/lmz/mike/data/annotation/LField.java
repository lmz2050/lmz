package cn.lmz.mike.data.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface LField {

	public String fieldName() default ""; //表中字段名称,默认为字段名
	public boolean useForDb() default true; //是否与db相关字段，默认是，
	public String sql() default "";         //用一个sql代替这个字段

}
