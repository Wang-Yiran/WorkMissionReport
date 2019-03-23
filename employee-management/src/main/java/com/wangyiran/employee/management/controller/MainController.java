package com.wangyiran.employee.management.controller;

import com.wangyiran.employee.management.entity.Report;
import com.wangyiran.employee.management.po.ReportReq;
import com.wangyiran.employee.management.service.MainService;
import com.wangyiran.employee.management.util.DateAdd8Hour;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @program: hello-springboot-thymeleaf
 * @description: 主控制器，实现首页的访问
 * @author: Mr.Wang
 * @create: 2019-03-19 23:55
 **/
@Controller
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String index(Model model){

        Report report = new Report();
        report.setUsername("Hello World!");

        model.addAttribute("user", report);
        return "index";
    }

    @RequestMapping(value = {"/report"}, method = RequestMethod.GET)
    public String report(Model model){
//        List<Report>  itemsList = mainService.SelectAll();

        List<Report>  itemsList = mainService.queryAll();
        model.addAttribute("itemsList", itemsList);

        model.addAttribute("page","1");
        model.addAttribute("offset","10");
        return "report";
    }

    @RequestMapping(value = {"/report", "/report/find"}, method = RequestMethod.POST)
    public String reportPost(Model model, @Param("report") ReportReq reportReq) {
        if(reportReq.getPage().isEmpty()){
            reportReq.setPage("1");
        }
        model.addAttribute("page", reportReq.getPage());
        model.addAttribute("offset", reportReq.getOffset());
        List<Report> reports = mainService.queryAll(reportReq);
        model.addAttribute("itemsList", reports);
        return "report";
    }

    @RequestMapping(value = {"/report/edit"}, method = RequestMethod.GET)
    public String reportEdit(Model model, @RequestParam String id) {
        if(id==null)
            return "";
        Report report = mainService.SelectById(Integer.valueOf(id));
        model.addAttribute("item", report);
        return "edit";
    }

    @RequestMapping(value = {"/report/edit"}, method = RequestMethod.POST)
    public String reportSubmit(Model model,@Param("item") Report report) {
        if(report==null){
            return "";
        }
        if(report.getId() == null) {
            return "";
        }
        //时间处理工具 获取的日期加了8小时，要加8小时，存入的世界时间才是当天
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        if(report.getStartdate()!=null) {
            date = report.getStartdate();
            cal.setTime(date);//设置起时间
            cal.add(Calendar.HOUR, 8);
            report.setStartdate(cal.getTime());
        }
        if(report.getEnddate()!=null) {
            date = report.getEnddate();
            cal.setTime(date);//设置起时间
            cal.add(Calendar.HOUR, 8);
            report.setEnddate(cal.getTime());
        }
        mainService.EditById(report);

        List<Report>  itemsList = mainService.queryAll();
        model.addAttribute("page","1");
        model.addAttribute("offset","10");
        model.addAttribute("itemsList", itemsList);
        return "redirect:/report";
    }

    @RequestMapping(value = {"/report/add"}, method = RequestMethod.POST)
    public String reportAddPost(Model model,@Param("item") Report report) {
        if(report==null){
            return "";
        }
        if(report.getId() != null) {
            return "";
        }

        //时间处理工具 获取的日期加了8小时，要加8小时，存入的世界时间才是当天
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        DateAdd8Hour dateAdd8Hour = new DateAdd8Hour();
        if(report.getStartdate()!=null) {
            report.setStartdate(dateAdd8Hour.dateAdd8hour(report.getStartdate()));
        }
        if(report.getEnddate()!=null) {
            report.setEnddate(dateAdd8Hour.dateAdd8hour(report.getEnddate()));
        }

        mainService.add(report);
//        Report report = mainService.SelectById(id);
//        Report report = new Report();
//        report.setUsername("Hello World!");
//
//        model.addAttribute("user", report);
//        model.addAttribute("item", report);

        List<Report>  itemsList = mainService.SelectAll();
        model.addAttribute("itemsList", itemsList);
        return "redirect:/report";
    }

    @RequestMapping(value = {"/report/add"}, method = RequestMethod.GET)
    public String reportAdd(Model model,@Param("item") Report report) {
        return "add";
    }

    @RequestMapping(value = {"/report/delete"}, method = RequestMethod.GET)
    public String reportAdd(Model model,@RequestParam String id) {
        if(id==null)
            return "";
        mainService.deleteById(Integer.valueOf(id));
        List<Report>  itemsList = mainService.queryAll();
        model.addAttribute("itemsList", itemsList);
        return "redirect:/report";
    }
}
