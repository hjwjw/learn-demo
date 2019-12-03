package io.github.hjwjw.infra.aspect;

import io.github.hjwjw.infra.annotation.ProcessResult;
import io.github.hjwjw.infra.annotation.SexValue;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * This SexAspect class.
 *
 * @author hjw
 * @date 2019/11/24 16:58
 */

@Component
@Aspect
public class SexAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(SexAspect.class);

    @AfterReturning(value = "@annotation(processResult)",returning = "result")
    public Object aftreReturning(JoinPoint joinPoint, ProcessResult processResult,Object result) throws IllegalAccessException {
        LOGGER.info(joinPoint.toString());
        LOGGER.info("<===================Aspect======================>");
        LOGGER.info(result.toString());

        if (result == null){
            return null;
        }
        if (result instanceof ResponseEntity){
            Object body = ((ResponseEntity<?>) result).getBody();
            if (body == null){
                return null;
            }
            if (body instanceof Collection ){
                for (Object obj : (Collection<?>) body){
                    //处理
                    processObj(obj);
                }
            }else {
                //处理
                processObj(body);
            }
        }else if (result instanceof Collection){
            //处理
            for (Object obj : (Collection<?>) result){
                //处理
                processObj(obj);
            }
        }else {
            processObj(result);

        }
        return result;
    }

    private void processObj(Object obj) throws IllegalAccessException {
        //取出Obj所有 Field，以及父类 Field
        List<Field> fieldList = new ArrayList<>();
        Class<?> tempClass  = obj.getClass();
        while (tempClass != null){
            fieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);

        //遍历Field,查找添加 @SexValue 注解的字段 并 做翻译
        for (Field field : fields){
            if (field.isAnnotationPresent(SexValue.class)){
                field.setAccessible(true);
                String fieldValue = String.valueOf(field.get(obj));
                LOGGER.info("fieldValue:{}",fieldValue);
                if ("M".equals(fieldValue)){
                    field.set(obj,"男");
                }else {
                    field.set(obj,"女");
                }
            }
        }

    }
}
