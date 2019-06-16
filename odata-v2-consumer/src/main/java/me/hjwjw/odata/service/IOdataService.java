package me.hjwjw.odata.service;

import me.hjwjw.odata.pojo.Product;

import java.util.List;

/**
 * The IOdataSapService class.
 *
 * @author HJW
 * @date 2019/02/28
 */
public interface IOdataService {

    List<Product> queryOdata(String odataUrl, String username, String password);


}
