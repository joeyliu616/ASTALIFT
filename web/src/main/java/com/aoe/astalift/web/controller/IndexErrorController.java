package com.aoe.astalift.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by joey on 16-3-12.
 */
@Controller
public class IndexErrorController implements ErrorController  {

    public static Logger logger = LoggerFactory.getLogger(IndexErrorController.class);

    @RequestMapping("/error")
    public void errorHandler(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer code = response.getStatus();
        if(code == 404){
            response.sendRedirect("/404.html");
        }else {
            response.sendRedirect("/500.html");
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
