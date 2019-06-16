package me.hjwjw.odata;

import me.hjwjw.odata.pojo.Product;
import me.hjwjw.odata.service.IOdataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.util.List;

@SpringBootApplication
public class OdataApplication implements CommandLineRunner {

    @Value("${odata.url}")
    private String odataUrl;

    @Value("${odata.username}")
    private String username;

    @Value("${odata.password}")
    private String password;

    @Resource
    private IOdataService service;

    public static void main(String[] args) {
        SpringApplication.run(OdataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<Product> productList = service.queryOdata(odataUrl,username,password);
        for(Product p : productList){
            System.out.println(p.toString());
            System.out.println("---------------------------------------------------");
        }
    }
}
