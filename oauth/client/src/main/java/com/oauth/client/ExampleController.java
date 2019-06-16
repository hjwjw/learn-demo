package com.oauth.client;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * The ExampleController class.
 *
 * @author hjwjw
 * @date 2019/04/14
 */
@RestController
public class ExampleController {

    @RequestMapping(value = "/")
    public String email(Principal principal){
        return "Hello" + principal.getName();
    }

}
