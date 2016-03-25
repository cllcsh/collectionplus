package org.express.common.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * bean注解类
 * @author Rei Ayanami
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface FieldMeta {
	/** 
     * 是否为主键
     * @return 
     */  
    boolean isPrimary() default false;
    /** 
     * 字段名称
     * @return 
     */  
    String name() default "";  
    /** 
     * 是否可编辑 
     * @return 
     */  
    boolean editable() default true;  
    /** 
     * 是否显示 
     * @return 
     */  
    boolean display() default true;  
    /** 
     * 字段描述 
     * @return 
     */  
    String description() default "";  
    /** 
     * 排序字段 
     * @return 
     */  
    int order() default 0;
    /**
     * 是否原生属性
     * @return
     */
    boolean isNative() default true;
}
