package cn.lmz.mike.data.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Lbean {
	public String keyName() default ""; //java中通过keyName找到这个Bean,默认为className
	public String tabName() default "";//java中通过tabName找到这表名,默认为className
}
