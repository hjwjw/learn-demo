package io.hjwjw.spring.service.impl;

import io.hjwjw.spring.entity.User;
import io.hjwjw.spring.service.ILdapService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import java.util.List;

/**
 * This io.hjwjw.spring.service.impl.LdapServiceImpl class.
 *
 * @author hjw
 * @date 2019/10/30 10:05
 */
@Service
@Slf4j
public class ILdapServiceImpl implements ILdapService {
    @Autowired
    private LdapTemplate ldapTemplate;

    @Value("${ldap.domainName}")
    private String ldapDomainName;

    @Value("${ldap.base}")
    private String ldapBaseDn;



    /**
     * 获取用户列表
     */
    public List<User> getPersonList(String ldapBase, Filter filter) {
        return ldapTemplate.search(ldapBase, filter.encode(), new AttributesMapper() {
            public User mapFromAttributes(Attributes attr) throws NamingException, javax.naming.NamingException {
                User person = new User();
                String distingugihedName = (String)attr.get("distinguishedName").get();
                person.setUserName((String)attr.get("username").get());
                person.setEmail((String)attr.get("mail").get());
                person.setRealName((String)attr.get("name").get());
                if (null != attr.get("mobile")) {
                    person.setMobile((String) attr.get("mobile").get());
                }
                if (null != attr.get("telephoneNumber")) {
                    person.setPhone((String) attr.get("telephoneNumber").get());
                }
                person.setLdapFlag(1);
                String departmentName = StringUtils.substringAfter(distingugihedName.split(",")[1], "OU=");
                person.setUnitName(departmentName);
                return person;
            }
        });
    }


    /**
     * 身份认证
     * @param userName
     * @param password
     * @return
     */
    public boolean authenticate(String userName, String password) {
        String userDomainName = String.format(ldapDomainName, userName);
        DirContext ctx = null;
        try {
            ctx = ldapTemplate.getContextSource().getContext(userDomainName,password);
            log.info("身份认证成功:",ctx);
            return true;

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            LdapUtils.closeContext(ctx);
        }

        return false;
    }

}
