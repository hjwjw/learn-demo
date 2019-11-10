package io.hjwjw.spring.entity;

import lombok.Data;
/**
 * This User class.
 *
 * @author hjw
 * @date 2019/10/30 14:59
 */

@Data
public class User {

    private String userName;

    private String realName;

    private String email;

    private String mobile;

    private String phone;

    private Integer ldapFlag;


    private String unitName;



}
