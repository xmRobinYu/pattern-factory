package com.gp.demo.mvc.action;


import com.gp.demo.service.IDemoService;
import com.gp.demo.service.IDemoService2;
import com.gp.mymvcframework.annotation.MyAutowired;
import com.gp.mymvcframework.annotation.MyController;
import com.gp.mymvcframework.annotation.MyRequestMapping;
import com.gp.mymvcframework.annotation.MyRequestParam;
import com.gp.mymvcframework.myspring.webmvc.servlet.MyModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


//虽然，用法一样，但是没有功能
@MyController
@MyRequestMapping("/demo")
public class DemoAction {

    @MyAutowired
    private IDemoService demoService;

    @MyAutowired
    private IDemoService2 demoService2;

    @MyRequestMapping("/query")
    public MyModelAndView query(HttpServletRequest req, HttpServletResponse resp,
                                @MyRequestParam("name") String name) {

        String result = "My name is " + name;
        try {
             demoService.get(name);
            return out(resp, result);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("detail", e.getCause().getMessage());
            model.put("stackTrace", Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]", ""));
            return new MyModelAndView("500", model);
        }
    }

    @MyRequestMapping("/add")
    public void add(HttpServletRequest req, HttpServletResponse resp,
                    @MyRequestParam("a") Integer a, @MyRequestParam("b") Integer b) {
        try {
            resp.getWriter().write(a + "+" + b + "=" + (a + b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @MyRequestMapping("/remove")
    public void remove(HttpServletRequest req, HttpServletResponse resp,
                       @MyRequestParam("id") Integer id) {
    }

    private MyModelAndView out(HttpServletResponse resp, String str) throws Exception {
        resp.getWriter().write(str);
        return null;
    }

}
