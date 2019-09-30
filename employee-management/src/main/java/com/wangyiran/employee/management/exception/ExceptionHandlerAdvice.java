package com.wangyiran.employee.management.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: employee-management
 * @description: 全局异常处理
 * @author: Mr.Wang
 * @create: 2019-09-29 15:03
 **/
@ControllerAdvice //只能捕捉controller层的异常
public class ExceptionHandlerAdvice {
//    @ResponseBody
//    @ExceptionHandler(value = Exception.class)
//    public Map exception(Exception exception) {
//        Map map = new HashMap();
//        map.put("errorMessage", exception.getMessage());
//        return map;
//    }

    @ExceptionHandler(value = Exception.class)
    public String exception(Exception exception, Model model) {
//        exception.printStackTrace();
        model.addAttribute("errorMessage", exception.getMessage());
        return "error/error";
    }
}
