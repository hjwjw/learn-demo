package me.hjwjw.odata.service.impl;

import me.hjwjw.odata.pojo.Product;
import me.hjwjw.odata.service.IOdataService;
import org.apache.commons.beanutils.BeanUtils;
import org.odata4j.consumer.ODataConsumer;
import org.odata4j.core.OClientBehaviors;
import org.odata4j.core.OEntity;
import org.odata4j.core.OProperty;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The OdataSapServiceImpl class.
 *
 * @author HJW
 * @date 2019/02/28
 */
@Service
public class OdataServiceImpl implements IOdataService {

    @Override
    public List<Product> queryOdata(String odataUrl, String username, String password) {
        ODataConsumer consumer;
        if (StringUtils.isEmpty(username)){
            consumer = ODataConsumer.create(odataUrl);
        }else{
            consumer = ODataConsumer.newBuilder(odataUrl)
                    .setClientBehaviors(OClientBehaviors.basicAuth(username, password))
                    .build();
        }
        List<Product> products = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        for (OEntity category : consumer.getEntities("Products").execute()) {
            Product product = new Product();
            Map<String,Object> propMap = new HashMap<>();
            for (OProperty property : category.getProperties()) {
                String name = property.getName().toLowerCase();
                //日期格式处理
                if (property.getType().getFullyQualifiedTypeName().equals("Edm.DateTime")) {
                    if (!ObjectUtils.isEmpty(property.getValue())){
                        try {
                            product.setReleaseDate(df.parse(property.getValue().toString()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
                propMap.put(name,property.getValue());
            }
            try {
                //给bean 赋值
                BeanUtils.populate(product,propMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            products.add(product);
        }
        return products;
    }
}
