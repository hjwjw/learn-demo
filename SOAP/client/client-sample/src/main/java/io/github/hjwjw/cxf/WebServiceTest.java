package io.github.hjwjw.cxf;


import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.beans.BeanUtils;

/**
 * CXF动态客户端 调用 WebService
 * 传递简单参数 或 JavaBean
 */
public class WebServiceTest {
    public static void main(String[] args) {
        //CXF动态客户端工厂
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();

        String wsdlUrl = "http://127.0.0.1:7856/ws/three/hello?wsdl";

        Object[] objects = null;
        //获取CXF客户端
        Client client = dcf.createClient(wsdlUrl);

        try {
            //调用WebService方法 传递复杂对象
            Person person = new Person();
            person.setName("HJW");
            person.setAge("8");
            person.setSex("m");
            /**
             *  命名空间需要与服务端保持一致，两种方式：
             *  1、直接与服务端保持一致，建立相同的包名
             *  2、通过
             *  Thread.currentThread().getContextClassLoader().loadClass("io.github.hjwjw.three.Person")
             *                     .newInstance();
             *  方式指定命名空间
             */
            Object personObject = Thread.currentThread().getContextClassLoader().loadClass("io.github.hjwjw.three.Person")
                    .newInstance();// 加载类为服务端自定义对象命名空间

            //赋值
            BeanUtils.copyProperties(person,personObject);
            //如果是简单字符类型，可直接传字符
            objects = client.invoke("sayHello",personObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //输出调用结果
        System.out.println(objects[0]);
    }
}
