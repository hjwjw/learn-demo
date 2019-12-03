package io.github.hjwjw.infra.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This SexValue class.
 * 性别翻译注解
 * @author hjw
 * @date 2019/11/24 16:57
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SexValue {

}
