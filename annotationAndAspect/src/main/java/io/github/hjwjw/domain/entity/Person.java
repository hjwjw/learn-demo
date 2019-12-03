package io.github.hjwjw.domain.entity;

import io.github.hjwjw.infra.annotation.SexValue;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * This Person class.
 *
 * @author hjw
 * @date 2019/11/24 15:51
 */
@Data
public class Person {

    private Long id;

    private String name;

    private Integer age;

    @SexValue
    private String sex;

    private String address;

    private String phoneNum;

    private LocalDateTime createdDate;

    private LocalDateTime lastUpdateDate;

    private Long objectVersionNumber;

    public void createBefore(){
        this.createdDate = LocalDateTime.now();
        this.lastUpdateDate = LocalDateTime.now();
        this.objectVersionNumber = 1L;
    }

    public void updateBefore(Long objectVersionNumber){
        this.lastUpdateDate = LocalDateTime.now();
        this.objectVersionNumber = objectVersionNumber + 1L;
    }
}
