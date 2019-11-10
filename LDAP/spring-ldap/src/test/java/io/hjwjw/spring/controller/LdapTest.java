package io.hjwjw.spring.controller;

import io.hjwjw.spring.App;
import io.hjwjw.spring.service.ILdapService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * This LdapControllerTest class.
 * LDAP 测试类
 * @author hjw
 * @date 2019/10/30 10:15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class LdapTest {



    @Autowired
    private ILdapService ILdapService;


    /**
     * LDAP 身份认证 spring实现方式
     */
    @Test
    public void springLdapConn(){
        ILdapService.authenticate("HJW","Hello001");
    }

    /**
     * LDAP 身份认证，验证登录
     */
    @Test
    public void ldapLoginTest(){
        AdLogin adLogin  = new AdLogin();
        adLogin.checkADLogin("HJW","Hello001");
    }

    /**
     * LDAP身份认证，验证登录并获取用户信息
     */
    @Test
    public void ldapLoginAdnSearchTest(){
        AdLoginAndSearch adLoginAndSearch = new AdLoginAndSearch();
        adLoginAndSearch.test("HJW","Hello001");
    }
}
