package io.github.hjwjw.sample.config;

import io.github.hjwjw.sample.app.service.HelloWorldService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * @Auther: jiangwei.huang@hand-china.com
 * @Date: 2019-9-20
 * @Description: webWebService 配置
 */
@Configuration
public class WebServiceConfig {

    private static Logger log = LoggerFactory.getLogger(WebServiceConfig.class);

    @Autowired
    private Bus bus;

    @Autowired
    private HelloWorldService helloWorldService;

    @Bean
    public ServletRegistrationBean dispatcherCXFServlet() {
        //发布服务名称
        return new ServletRegistrationBean(new CXFServlet(), "/service/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public Bus springBus() {
        return new SpringBus();
    }

    @Bean
    public Endpoint addEndpoint() {

        EndpointImpl endpoint = new EndpointImpl(bus, helloWorldService);
        endpoint.publish("/hello");
        log.info("发布成功！");
        return endpoint;
    }

}
