package io.github.hjwjw.two;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * The HelloWorldThree class.
 * 简单的WebService发布
 * @author
 * @date
 */

/**
 * @WebService 注解声明一个WebServier对外发布，public 方法都会默认发布
 * 被声明的类中必须有一个公开的方法，否则发布失败
 */
@WebService(
        serviceName = "HelloWorl2", //  服务名
        targetNamespace = "http://two.hjwjw.github.io"  //命名空间,默认包名取反
)
public class HelloWorldTwo {



    
    @WebMethod(operationName = "sayHiWithYou") //对外发布的方法名
    public @WebResult(name = "toYou") String sayHi(@WebParam(name = "youName") String name) {
        return "Hi,"+name;
    }

    @WebMethod(exclude = true) //表示排除这个方法
    public String sayHello(String name) {
        return "Hello,"+name;
    }


    /**
     *     protected、private、final、static方法不能对外公开
     */
    private String sayLove(String name){
        return "love you,"+ name;
    }

    protected String sayNo(String name){
        return "No,"+ name;
    }

    public static String sayStatic(String name){
        return "Static,"+name;
    }

    public final String sayFinal(String name){
        return "final,"+name;
    }
}
