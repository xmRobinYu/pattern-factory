package com.gp.demo.mvc.action;

import com.gp.demo.service.IDemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TwoAction {

    private IDemoService demoService;

    public void edit(HttpServletRequest req, HttpServletResponse resp,
                     String name) {
        try {
            String result = demoService.get(name);
            resp.getWriter().write(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
