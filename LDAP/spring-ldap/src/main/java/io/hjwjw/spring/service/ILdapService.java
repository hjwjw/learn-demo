package io.hjwjw.spring.service;

import io.hjwjw.spring.entity.User;
import org.springframework.ldap.filter.Filter;

import java.util.List;

/**
 * This io.hjwjw.spring.service.LdapService class.
 *
 * @author hjw
 * @date 2019/10/30 10:05
 */
public interface ILdapService {

    List<User> getPersonList(String ldapBase, Filter filter);

    boolean authenticate(String userName, String password);
}
