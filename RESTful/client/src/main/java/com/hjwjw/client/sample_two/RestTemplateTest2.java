package com.hjwjw.client.sample_two;


import com.hjwjw.client.Greeting;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The RestTemplateTest class.
 * 简单使用RestTemplate的get与post请求，并使用请求拦截器给所有请求添加统一的header信息
 *
 * @author HJW
 * @date 2019/05/12
 */
public class RestTemplateTest2 {

    private static final Logger log = LoggerFactory.getLogger(RestTemplateTest2.class);
    private RestTemplate restTemplate ;

    @Before
    public void init(){
        restTemplate = new RestTemplate();
        //添加请求拦截器，会给所有请求添加Headers
        restTemplate.setInterceptors(Collections.singletonList(new UserAgentInterceptor()));
    }


    /**
     * 测试 RestTemplate 中的 GET方式访问方法 getForEntity()
     * ResponseEntity<T> 接收返回，与getForObject相比返回中多了状态码与headers信息
     */
    @Test
    public void testGetForEntity(){

        //不带参数
        String url = "http://localhost:8080/greetingByGet";
        ResponseEntity<Greeting> greeting = restTemplate.getForEntity(url,Greeting.class);
        log.info("Status：{}",greeting.getStatusCode());
        log.info("Body：{}",greeting.getBody());
        log.info("Headers：{}",greeting.getHeaders());


        //带参数,拼接参数
        url = "http://localhost:8080/greetingByGet?firstName=JW";
        greeting = restTemplate.getForEntity(url,Greeting.class);
        log.info("Status：{}",greeting.getStatusCode());
        log.info("Body：{}",greeting.getBody());
        log.info("Headers：{}",greeting.getHeaders());


        //带参数,使用?作为参数点位符
        url = "http://localhost:8080/greetingByGet?firstName={?}&lastName={?}";
        greeting = restTemplate.getForEntity(url,Greeting.class,"JW","H");
        log.info("Status：{}",greeting.getStatusCode());
        log.info("Body：{}",greeting.getBody());
        log.info("Headers：{}",greeting.getHeaders());


        //带参数,使用Map传参：url中的{key} 与 map.key 对应
        url = "http://localhost:8080/greetingByGet?firstName={fName}&lastName={lName}";
        Map<String,Object> params = new HashMap<>();
        params.put("fName","JW");
        params.put("lName","H");
        greeting = restTemplate.getForEntity(url,Greeting.class,params);
        log.info("Status：{}",greeting.getStatusCode());
        log.info("Body：{}",greeting.getBody());
        log.info("Headers：{}",greeting.getHeaders());

        //带参数,使用URI封装
        URI uri = URI.create( "http://localhost:8080/greetingByGet?firstName=JW&lastName=H");
        greeting = restTemplate.getForEntity(uri,Greeting.class);
        log.info("Status：{}",greeting.getStatusCode());
        log.info("Body：{}",greeting.getBody());
        log.info("Headers：{}",greeting.getHeaders());
    }

    /**
     * 测试 RestTemplate 中的 POST方式访问方法 getPostEntity()
     * ResponseEntity<T> 接收返回，与postForObject相比返回中多了状态码与headers信息
     * POST请求中添加headers信息除了使用请求拦截器 还可以使用 request 中添加headers
     */
    @Test
    public void testPostForEntity(){
        RestTemplate restTemplate2 = new RestTemplate();
        String url = "http://localhost:8080/greetingByPost";
        String firstName="JW";
        String lastName="H";

        MultiValueMap<String,Object> params = new LinkedMultiValueMap<>();
        params.add("firstName", firstName);
        params.add("lastName", lastName);


        //带参数
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.USER_AGENT,"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(params,httpHeaders);
        ResponseEntity<Greeting> greeting = restTemplate2.postForEntity(url, request, Greeting.class);
        log.info("Status：{}",greeting.getStatusCode());
        log.info("Body：{}",greeting.getBody());
        log.info("Headers：{}",greeting.getHeaders());
    }

    /**
     * 测试 RestTemplate 中的 exchange() 方法
     * exchage() 更灵活，可以指定是使用POST请求还是GET请求
     */
    @Test
    public void testExchange(){
        RestTemplate restTemplate2 = new RestTemplate();
        String url = "http://localhost:8080/greetingByPost";
        String firstName="JW";
        String lastName="H";

        MultiValueMap<String,Object> params = new LinkedMultiValueMap<>();
        params.add("firstName", firstName);
        params.add("lastName", lastName);

        //带参数
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.USER_AGENT,"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(params,httpHeaders);
        ResponseEntity<Greeting> greeting = restTemplate2.exchange(url, HttpMethod.POST, request, Greeting.class);
        log.info("Status：{}",greeting.getStatusCode());
        log.info("Body：{}",greeting.getBody());
        log.info("Headers：{}",greeting.getHeaders());
    }

}
