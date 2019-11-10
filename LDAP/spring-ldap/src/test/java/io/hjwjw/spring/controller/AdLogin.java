package io.hjwjw.spring.controller;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

/**
 * This AdLogin class.
 *  AD 域 身份认证 - Java
 * @author hjw
 * @date 2019/10/30 15:46
 */
public class AdLogin {

    /**
     *
     * @Description 域登录验证
     * @param userName AD域认证，用户的登录UserName
     * @param password  AD域认证，用户的登录PassWord
     * @return loginflag
     */
    public boolean checkADLogin(String userName,String password ) {
        boolean loginflag=false;
        //AD域IP
        String host = "127.0.0.1";
        String domain = "alicocorp";
        String port = "389";
        String url = "ldap://" + host + ":" + port;
        String user = domain+"\\"+userName;
        System.out.println("url"+url);
        Hashtable env = new Hashtable();
        DirContext ctx = null;
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, user); //
        env.put(Context.SECURITY_CREDENTIALS, password);
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, url);
        try {
            ctx = new InitialDirContext(env);// 初始化上下文
            System.out.println("身份验证成功!");
            loginflag=true;
        } catch (AuthenticationException e) {
            System.out.println("身份验证失败!");
            e.printStackTrace();
        } catch (javax.naming.CommunicationException e) {
            System.out.println("AD域连接失败!");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("身份验证未知异常!");
            e.printStackTrace();
        } finally{
            if(null!=ctx){
                try {
                    ctx.close();
                    ctx=null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return loginflag;
    }
}
