package com.hjwjw.server;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The GreetingController class.
 *
 * @author HJW
 * @date 2019/03/28
 */
@RestController
public class GreetingController {

    private static final String template = "Hello, %s.%s";
    private static final String success_template = "welcome, %s";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping(value = "/greetingByGet")
    public Greeting greetingByGet(HttpServletRequest httpServletRequest, @RequestParam(value = "firstName", defaultValue = "World") String firstName,
                             @RequestParam(value = "lastName", defaultValue = "lastName") String lastName) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, firstName, lastName));
    }

    @PostMapping(value = "/greetingByPost")
    public Greeting greetingByPost(HttpServletRequest httpServletRequest, @RequestParam(value = "firstName", defaultValue = "World") String firstName,
                             @RequestParam(value = "lastName", defaultValue = "lastName") String lastName) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, firstName, lastName));
    }

    @RequestMapping(path = "/login", method = {RequestMethod.GET, RequestMethod.OPTIONS, RequestMethod.POST})
    public String login(HttpServletRequest httpServletRequest, @RequestParam(value = "userName", defaultValue = "World") String userName,
                     @RequestParam(value = "password", defaultValue = "lastName") String password) {
        return "redirect:/success?userName=" + userName + "&status=success";
    }

    @RequestMapping(path = "/success")
    @ResponseBody
    public String success(@RequestParam(value = "userName", defaultValue = "World") String userName,
                          @RequestParam(value = "password", defaultValue = "lastName") String password) {
        return String.format(success_template, userName);

    }
}
