package io.github.hjwjw.rpc;

import com.alibaba.fastjson.JSON;
import io.github.hjwjw.cxf.Person;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

/**
 * This RpcApp class.
 * WebService RPCf远程调用示例
 * @author jiangwei.huang@hand-china.com
 * @date 2019/12/17 19:53
 */
public class RpcApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcApp.class);

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("HJW");
        person.setAge("8");
        person.setSex("m");
        List<Object> paramList = new ArrayList<>();
        paramList.add(JSON.toJSONString(person));
        callWebServiceByrpc("http://127.0.0.1:8080/service/hello?wsdl",
                "sayHello",
                "http://service.app.sample.hjwjw.github.io",
                paramList);
    }

    public static String callWebServiceByrpc(String wsdlUrl, String methodName, String targetNamespace,List<Object> paramList){
        try {
            // 使用RPC方式调用WebService
            RPCServiceClient serviceClient = new RPCServiceClient();
            // 指定调用WebService的URL
            EndpointReference targetEPR = new EndpointReference(wsdlUrl);
            Options options = serviceClient.getOptions();
            // 确定目标服务地址
            options.setTo(targetEPR);
            QName qname = new QName(targetNamespace, methodName);
            // 指定参数
            Object param = paramList.get(0);
            Object[] parameters = new Object[]{paramList.get(0)};
            LOGGER.info("WebService Send data:{}", JSON.toJSON(parameters));
            //这是针对返值类型的
            Class<?>[] types = new Class[]{String.class};
            // 调用方法一 传递参数，调用服务，获取服务返回结果集
            Object[] response = serviceClient.invokeBlocking(qname,parameters,types);
            LOGGER.info("Response:{}",response[0]);

        } catch (AxisFault e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String callWebServiceByrpc2(String wsdlUrl, String methodName, String targetNamespace, String name, List<Object> paramList){
        try {

            ServiceClient sender = new ServiceClient();
            // 指定调用WebService的URL
            EndpointReference targetEPR = new EndpointReference(wsdlUrl);
            Options options = new Options();
            // 确定目标服务地址
            options.setTo(targetEPR);
            sender.setOptions(options);
            OMFactory fac = OMAbstractFactory.getOMFactory();
            OMNamespace omNs = fac.createOMNamespace(targetNamespace, "");
            OMElement method = fac.createOMElement(methodName, omNs);
            OMElement symbol = fac.createOMElement("arg0", omNs);
            // symbol.setText("1");
            symbol.addChild(fac.createOMText(symbol, "{\"age\":\"8\",\"name\":\"HJW\",\"sex\":\"m\"}"));
            method.addChild(symbol);
            method.build();
            OMElement result = sender.sendReceive(method);
            LOGGER.info("<====result:{}",result);

        } catch (AxisFault e) {
            e.printStackTrace();
        }
        return "";
    }
}
