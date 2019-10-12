package com.hjwjw.client.sample_one;


import com.hjwjw.client.Greeting;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * The RestTemplateTest class.
 * 简单使用RestTemplate的get与post请求
 *
 * @author HJW
 * @date 2019/05/12
 */
public class RestTemplateTest {

    private static final Logger log = LoggerFactory.getLogger(RestTemplateTest.class);
    private RestTemplate restTemplate ;

    @Before
    public void init(){
        restTemplate = new RestTemplate();
    }

    /**
     * 测试 RestTemplate 中的 GET方式访问方法 getForObject()
     */
    @Test
    public void testGetForObject(){

        //不带参数
        String url = "http://localhost:8080/greetingByGet";
        Greeting greeting = restTemplate.getForObject(url,Greeting.class);
        log.info("方法一   {}",greeting.toString());


        //带参数,拼接参数
        url = "http://localhost:8080/greetingByGet?firstName=JW";
        greeting = restTemplate.getForObject(url,Greeting.class);
        log.info("方法二    {}",greeting.toString());


        //带参数,使用?作为参数点位符
        url = "http://localhost:8080/greetingByGet?firstName={?}&lastName={?}";
        greeting = restTemplate.getForObject(url,Greeting.class,"JW","H");
        log.info("方法三    {}",greeting.toString());


        //带参数,使用Map传参：url中的{key} 与 map.key 对应
        url = "http://localhost:8080/greetingByGet?firstName={fName}&lastName={lName}";
        Map<String,Object> params = new HashMap<>();
        params.put("fName","JW");
        params.put("lName","H");
        greeting = restTemplate.getForObject(url,Greeting.class,params);
        log.info("方法四    {}",greeting.toString());


        //带参数,使用URI封装
        URI uri = URI.create( "http://localhost:8080/greetingByGet?firstName=JW&lastName=H");
        greeting = restTemplate.getForObject(uri,Greeting.class);
        log.info("方法五    {}",greeting.toString());
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
     * 测试 RestTemplate 中的 POST方式访问方法 getPostObject()
     */
    @Test
    public void testPostForObject(){

        String url = "http://localhost:8080/greetingByPost";
        String firstName="JW";
        String lastName="H";

        MultiValueMap<String,Object> request = new LinkedMultiValueMap<>();
        request.add("firstName", firstName);
        request.add("lastName", lastName);

        //不带参数
        Greeting greeting = restTemplate.postForObject(url, null, Greeting.class);
        log.info("方法一   {}",greeting.toString());

        //带参数
        greeting = restTemplate.postForObject(url, request, Greeting.class);
        log.info("方法二   {}",greeting.toString());


        //使用URI
        URI uri = URI.create(url);
        greeting = restTemplate.postForObject(uri, request, Greeting.class);
        log.info("方法三   {}",greeting.toString());

        //带参数，结合表单传参与uri拼接参数,使用 ?
        request.clear();
        request.add("firstName", firstName);
        greeting = restTemplate.postForObject(url + "?lastName={?}", request, Greeting.class,"H");
        log.info("方法四   {}",greeting.toString());

        //带参数，结合表单传参与uri拼接参数,使用map
        Map<String,Object> params = new HashMap<>();
        params.put("lastName","H");
        greeting = restTemplate.postForObject(url + "?lastName={lastName}", request, Greeting.class,params);
        log.info("方法四   {}",greeting.toString());
    }

    /**
     * 测试 RestTemplate 中的 POST方式访问方法 getPostEntity()
     * ResponseEntity<T> 接收返回，与postForObject相比返回中多了状态码与headers信息
     */
    @Test
    public void testPostForEntity(){
        String url = "http://localhost:8080/greetingByPost";
        String firstName="JW";
        String lastName="H";

        MultiValueMap<String,Object> request = new LinkedMultiValueMap<>();
        request.add("firstName", firstName);
        request.add("lastName", lastName);

        //不带参数
        ResponseEntity<Greeting> greeting = restTemplate.postForEntity(url, null, Greeting.class);
        log.info("Status：{}",greeting.getStatusCode());
        log.info("Body：{}",greeting.getBody());
        log.info("Headers：{}",greeting.getHeaders());

        //带参数
        greeting = restTemplate.postForEntity(url, request, Greeting.class);
        log.info("Status：{}",greeting.getStatusCode());
        log.info("Body：{}",greeting.getBody());
        log.info("Headers：{}",greeting.getHeaders());


        //使用URI
        URI uri = URI.create(url);
        greeting = restTemplate.postForEntity(uri, request, Greeting.class);
        log.info("Status：{}",greeting.getStatusCode());
        log.info("Body：{}",greeting.getBody());
        log.info("Headers：{}",greeting.getHeaders());

        //带参数，结合表单传参与uri拼接参数,使用 ?
        request.clear();
        request.add("firstName", firstName);
        greeting = restTemplate.postForEntity(url + "?lastName={?}", request, Greeting.class,"H");
        log.info("Status：{}",greeting.getStatusCode());
        log.info("Body：{}",greeting.getBody());
        log.info("Headers：{}",greeting.getHeaders());

        //带参数，结合表单传参与uri拼接参数,使用map
        Map<String,Object> params = new HashMap<>();
        params.put("lastName","H");
        greeting = restTemplate.postForEntity(url + "?lastName={lastName}", request, Greeting.class,params);
        log.info("Status：{}",greeting.getStatusCode());
        log.info("Body：{}",greeting.getBody());
        log.info("Headers：{}",greeting.getHeaders());
    }

    /**
     * 测试 RestTemplate 中的 POST方式访问方法 postForLocation()
     * postForLocation() 返回的是一个URI
     * 常用作登录或注册，请求到后台，验证通过后跳转到其它资源。如POST一个URI到postForLocation，按不同逻辑返回一个新的URI
     */
/*    @Test
    public void testPostForLocation(){

        String url = "http://localhost:8080/login";
        String userName="HJW";
        String password="123456";

        MultiValueMap request = new LinkedMultiValueMap();
        request.add("userName", userName);
        request.add("password", password);

        URI greetingUri = restTemplate.postForLocation(url, request);
        log.info("URI:  {}",greetingUri);

    }*/
}
