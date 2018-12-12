package com.lmz.mike.data.util;

import com.lmz.mike.data.annotation.Lbean;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;

/**
 * 2 * @Author: Mike Lee
 * 3 * @Date: 2018/12/9 17:48
 * 4
 */
public class ClassScanner extends ClassPathBeanDefinitionScanner {

    private Class type;

    public ClassScanner(BeanDefinitionRegistry registry, Class<? extends Annotation> type){
        super(registry,false);
        this.type = type;
    }
    /**
     * 注册 过滤器
     */
    public void registerTypeFilter(){
        addIncludeFilter(new AnnotationTypeFilter(type));
    }

    public void testSimpleScan() {
        String BASE_PACKAGE = "com.lmz";
        GenericApplicationContext context = new GenericApplicationContext();
        ClassScanner myClassPathDefinitonScanner = new ClassScanner(context, Lbean.class);
        // 注册过滤器
        myClassPathDefinitonScanner.registerTypeFilter();
        int beanCount = myClassPathDefinitonScanner.scan(BASE_PACKAGE);
        context.refresh();
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(beanCount);
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
    }
}
