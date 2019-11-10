package io.hjwjw.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

/**
 * This io.hjwjw.spring.config.LdapConfig class.
 *
 * @author hjw
 * @date 2019/10/30 10:04
 */
@Configuration
public class LdapConfig {
    @Value("${ldap.url}")
    private String ldapUrl;

    @Value("${ldap.base}")
    private String ldapBase;

    @Value("${ldap.userDn}")
    private String ldapUserDn;

    @Value("${ldap.userPwd}")
    private String ldapUserPwd;

    @Value("${ldap.referral}")
    private String ldapReferral;


    /**
     * SpringLdap的javaConfig注入方式
     */
    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSourceTarget());
    }

    /**
     * SpringLdap的javaConfig注入方式
     */
    @Bean
    public LdapContextSource contextSourceTarget() {
        LdapContextSource ldapContextSource = new LdapContextSource();

        ldapContextSource.setUrl(ldapUrl);
        ldapContextSource.setBase(ldapBase);
        ldapContextSource.setUserDn(ldapUserDn);
        ldapContextSource.setPassword(ldapUserPwd);
        ldapContextSource.setReferral(ldapReferral);
        return ldapContextSource;
    }
}


